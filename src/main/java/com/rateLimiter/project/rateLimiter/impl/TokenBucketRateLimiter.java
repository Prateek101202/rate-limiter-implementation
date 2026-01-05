package com.rateLimiter.project.rateLimiter.impl;

import com.rateLimiter.project.model.User;
import com.rateLimiter.project.rateLimiter.RateLimiter;
import com.rateLimiter.project.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class TokenBucketRateLimiter implements RateLimiter{
    @Autowired private UserRepository userRepository;
    @Override
    public Boolean limit(final User user) {
        if (user == null || StringUtils.isBlank(user.getUserId())) {
            throw new IllegalArgumentException("Invalid userId");
        }
        User userInDb = userRepository.getUserById(user.getUserId())
                .orElseThrow(() -> new IllegalStateException("User not registered"));
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime lastRefill = userInDb.getLastRefillTime();

        BigDecimal elapsedHours = getHours(lastRefill);
        if (elapsedHours.compareTo(BigDecimal.ZERO) < 0) {
            elapsedHours = BigDecimal.ZERO;
        }
        BigDecimal newTokens =
                userInDb.getAvailableTokens()
                        .add(userInDb.getLevel().getRefillRate().multiply(elapsedHours))
                        .min(userInDb.getLevel().getMaxTokens());
        userInDb.setLastRefillTime(now);
        if (newTokens.compareTo(BigDecimal.ONE) < 0) {
            return false;
        }
        userInDb.setAvailableTokens(newTokens.subtract(BigDecimal.ONE));
        userRepository.save(userInDb);
        return true;
    }


    private BigDecimal getHours(final ZonedDateTime time) {
        final ZonedDateTime now = ZonedDateTime.now();
        final Duration duration = Duration.between(time, now);
        final BigDecimal seconds = BigDecimal.valueOf(duration.getSeconds());
        return seconds.divide(
                BigDecimal.valueOf(3600),
                2,
                RoundingMode.HALF_DOWN
        );
    }
}

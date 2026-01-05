package com.rateLimiter.project.service;

import com.rateLimiter.project.model.User;
import com.rateLimiter.project.rateLimiter.RateLimiter;
import com.rateLimiter.project.rateLimiter.factory.RateLimiterFactory;
import com.rateLimiter.project.rateLimiter.type.RateLimitType;
import org.springframework.stereotype.Component;

@Component
public class RateLimiterService {
    public Boolean limit(final User user){
        final RateLimitType rateLimitType = RateLimitType.TOKEN_BUCKET;
        final RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(rateLimitType);
        return rateLimiter.limit(user);
    }
}

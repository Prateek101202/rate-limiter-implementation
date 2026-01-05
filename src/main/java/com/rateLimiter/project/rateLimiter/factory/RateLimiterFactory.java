package com.rateLimiter.project.rateLimiter.factory;

import com.rateLimiter.project.rateLimiter.RateLimiter;
import com.rateLimiter.project.rateLimiter.type.RateLimitType;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class RateLimiterFactory {
    private final Map<RateLimitType, RateLimiter> RATE_LIMITER_MAP= new HashMap<>();

    public static void register(final RateLimitType rateLimitType, final RateLimiter rateLimiter ){
        RATE_LIMITER_MAP.put(rateLimitType, rateLimiter);
    }

    public static RateLimiter getRateLimiter(final RateLimitType rateLimitType){
        return RATE_LIMITER_MAP.getOrDefault(rateLimitType, null);
    }
}

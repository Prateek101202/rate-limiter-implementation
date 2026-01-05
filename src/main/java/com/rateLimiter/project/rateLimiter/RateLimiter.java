package com.rateLimiter.project.rateLimiter;

import com.rateLimiter.project.model.User;

public interface RateLimiter {
    Boolean limit(User user);
}

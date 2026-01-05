package com.rateLimiter.project.controller;

import com.rateLimiter.project.model.User;
import com.rateLimiter.project.service.RateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiter {

    @Autowired private RateLimiterService rateLimiterService;
    @GetMapping("/limit")
    public ResponseEntity<Boolean> rateLimit(@RequestBody final User user){
        return ResponseEntity.ok(rateLimiterService.limit(user));
    }
}

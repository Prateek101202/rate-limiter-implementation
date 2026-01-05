package com.rateLimiter.project.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public String handleException(final Exception e){
        return e.getMessage();
    }
}

package com.rateLimiter.project.model.UserLevel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public enum Level {
    STANDARD(BigDecimal.valueOf(2), BigDecimal.valueOf(1)),
    GOLD(BigDecimal.valueOf(5), BigDecimal.valueOf(2)),
    PLATINUM(BigDecimal.valueOf(10), BigDecimal.valueOf(4));

    private BigDecimal maxTokens;
    private BigDecimal refillRate;

    public BigDecimal getMaxTokens(){
        return this.maxTokens;
    }

    public BigDecimal getRefillRate(){
        return this.refillRate;
    }
}

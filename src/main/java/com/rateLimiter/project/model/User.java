package com.rateLimiter.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.rateLimiter.project.model.UserLevel.Level;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@Document(collection = "User")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    private String userName;
    @Builder.Default
    private BigDecimal availableTokens = Level.STANDARD.getMaxTokens();
    @Builder.Default
    private Level level = Level.STANDARD;
    private ZonedDateTime lastRefillTime;

    public void setLevel(final Level level){
        this.level = level;
        availableTokens = level.getMaxTokens();
    }
}

package com.hoellem.groupmebot.config;

import com.hoellem.groupmebot.client.RedditAuthClient;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

@RequiredArgsConstructor
public class RedditClientConfig
{

    private static final TemporalAmount tokenTimeout = Duration.ofSeconds(3600);
    private final RedditAuthClient redditAuthClient;
    private Instant lastTokenRefresh = Instant.EPOCH;
    private String accessToken;

    @Bean
    RequestInterceptor requestInterceptor() {
        if (lastTokenRefresh.plus(tokenTimeout).isBefore(Instant.now()) || accessToken == null) {
            accessToken = redditAuthClient.getAccessToken().getAccessToken();
            lastTokenRefresh = Instant.now();
        }
        return requestTemplate -> requestTemplate
                .header("Authorization", "Bearer " + accessToken)
                .header("User-Agent", "AIDS Bot 1.1");
    }
}

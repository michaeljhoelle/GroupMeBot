package com.hoellem.groupmebot.config;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@RequiredArgsConstructor
public class RedditAuthClientConfig
{
    @Value("${reddit.auth.client-authorization}")
    private String clientAuth;
    @Value("${reddit.auth.username}")
    private String username;
    @Value("${reddit.auth.password}")
    private String password;

    private final SpringFormEncoder encoder;

    @Bean("RedditAuthInterceptor")
    RequestInterceptor requestInterceptor() {
        Map<String, String> paramsMap = Map.of(
                "grant_type", "password",
                "username", username,
                "password", password,
                "scope", "mysubreddits,identity,read");
        return requestTemplate -> {
            requestTemplate.header("Authorization", clientAuth).header("User-Agent", "AIDS Bot 1.1");
            encoder.encode(paramsMap, Encoder.MAP_STRING_WILDCARD, requestTemplate);
        };
    }
}

package com.hoellem.groupmebot.client;

import com.hoellem.groupmebot.config.RedditAuthClientConfig;
import com.hoellem.groupmebot.model.reddit.AuthResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "reddit-auth", url = "https://ssl.reddit.com/api/v1/access_token", configuration = RedditAuthClientConfig.class)
public interface RedditAuthClient
{
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AuthResponse getAccessToken();
}

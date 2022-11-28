package com.hoellem.groupmebot.client;

import com.hoellem.groupmebot.config.RedditClientConfig;
import com.hoellem.groupmebot.model.reddit.SubredditResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reddit", url = "https://oauth.reddit.com", configuration = RedditClientConfig.class)
public interface RedditClient
{
    @GetMapping("/r/{subreddit}/hot")
    SubredditResponseWrapper getHot(@PathVariable("subreddit") String subreddit);
}

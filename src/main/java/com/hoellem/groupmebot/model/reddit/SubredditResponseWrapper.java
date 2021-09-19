package com.hoellem.groupmebot.model.reddit;

import lombok.Data;

import java.util.List;

@Data
public class SubredditResponseWrapper
{
    private String kind;
    private SubredditResponse data;

    @Data
    public static class SubredditResponse {
        private List<SubredditResponseWrapper> children;
        private String subreddit;
        private String title;
        private String url;
        private String selftext;
    }
}

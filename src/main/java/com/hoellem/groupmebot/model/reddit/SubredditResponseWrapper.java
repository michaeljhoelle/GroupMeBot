package com.hoellem.groupmebot.model.reddit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

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
        @JsonProperty("media_metadata")
        private Map<String, MediaMetadata> mediaMetadataMap;
    }

    @Data
    public static class MediaMetadata {
        @JsonProperty("s")
        private Metadatum source;
    }

    @Data
    public static class Metadatum {
        @JsonProperty("u")
        private String url;
    }

}

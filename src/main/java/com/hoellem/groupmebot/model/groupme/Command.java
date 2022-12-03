package com.hoellem.groupmebot.model.groupme;

import com.hoellem.groupmebot.model.reddit.Subreddit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Command
{
    FEET(List.of("^/feet\\b", "^/feetcount\\b")),
    CLS(List.of("^/cls3\\b")),
    TODAY(List.of("^Is (?:it|today) ([^?]+)\\??")),
    SUBREDDIT_POST(Arrays.stream(Subreddit.values()).map(Subreddit::getShortName).map(str -> "^/" + str + "\\b").collect(Collectors.toList())),
    SUBREDDIT_FIND(List.of("^r/\\w+$")),
    TODO(List.of("^/todo\\b"));

    private final List<String> patterns;
}

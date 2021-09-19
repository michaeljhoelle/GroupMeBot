package com.hoellem.groupmebot.model.groupme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Command
{
    FEET(List.of("^/feet\\b", "^/feetcount\\b")),
    CLS(List.of("^/cls3\\b")),
    TODAY(List.of("^is (it|today) \\S+$")),
    SUBREDDIT_POST(List.of("^/suffer\\b", "^/beans\\b")),
    SUBREDDIT_FIND(List.of("^r/\\w+$")),
    TODO(List.of("^/todo\\b"));

    private final List<String> patterns;
}

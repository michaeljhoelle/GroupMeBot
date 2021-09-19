package com.hoellem.groupmebot.model.reddit;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

@RequiredArgsConstructor
public enum Subreddit
{
    SUFFER("suffer", "MakeMeSuffer"),
    BEANS("beans", "BeansInThings");

    private final String shortName;
    private final String subreddit;

    public static Subreddit fromShortName(String shortName) {
        for (Subreddit subreddit : Subreddit.values()) {
            if (subreddit.shortName.equals(shortName)) {
                return subreddit;
            }
        }
        throw new UnsupportedOperationException("Unknown subreddit");
    }
}

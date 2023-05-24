package com.hoellem.groupmebot.model.reddit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Subreddit
{
    SUFFER("suffer", "MakeMeSuffer"),
    BEANS("beans", "BeansInThings"),
    EYE_BLEACH("cls", "EyeBleach");

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

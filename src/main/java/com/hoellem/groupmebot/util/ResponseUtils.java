package com.hoellem.groupmebot.util;

import java.util.List;
import java.util.Random;

public class ResponseUtils
{
    private static final List<String> staleResponses = List.of(
            "Nothing new right now, boss",
            "Give me a bit, this content is old",
            "Maybe try again tomorrow",
            "Maybe Mike needs to make the bot better",
            "You could always ask for a meme, or a quote, or something"
    );

    private static final Random random = new Random();

    public static String getStaleResponse() {
        return staleResponses.get(random.nextInt(staleResponses.size()));
    }

    public final static int MIKES_USER_ID = 13271487;
}

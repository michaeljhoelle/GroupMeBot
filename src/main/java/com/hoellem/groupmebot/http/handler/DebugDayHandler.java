package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.Command;
import com.hoellem.groupmebot.model.groupme.FindGroupDetailsResponse;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import com.hoellem.groupmebot.model.groupme.GroupMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hoellem.groupmebot.util.ResponseUtils.MIKES_USER_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebugDayHandler implements RequestHandler {
    private final GroupMeMessenger messenger;
    private final DayHandler dayHandler;

    @Override
    public void handle(GroupMeRequest request) {
        if (request.getUserId().equals(MIKES_USER_ID)) {
            String responseText = String.join("\n", new HashSet<>(dayHandler.validCurrentTimes()));
            messenger.sendGroupMeMessage(responseText);
        }
    }
}

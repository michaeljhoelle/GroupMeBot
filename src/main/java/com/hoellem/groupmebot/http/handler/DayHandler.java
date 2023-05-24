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
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class DayHandler implements RequestHandler {
    private static final Pattern dayPattern = Pattern.compile(Command.TODAY.getPatterns().get(0), Pattern.CASE_INSENSITIVE);
    private final GroupMeMessenger messenger;

    @Override
    public void handle(GroupMeRequest request) {
        Matcher matcher = dayPattern.matcher(request.getText());
        if (matcher.find()) {
            String responseText = isToday(matcher.group(1)) ? "Yes" : "No";

            messenger.sendGroupMeMessage(responseText);
        }
    }

    public Collection<String> validCurrentTimes() {
        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.of("-05:00"));
        TextStyle textStyle = TextStyle.FULL;
        Locale locale = Locale.US;
        return Stream.of(
                        "now",
                        now.getYear(),
                        now.getMonth().getDisplayName(textStyle, locale),
                        "the " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
                        now.getMonth().getDisplayName(textStyle, locale) + " " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
                        now.getMonth().getDisplayName(TextStyle.SHORT, locale) + " " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
                        now.getMonth().getDisplayName(textStyle, locale) + " the " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
                        now.getMonth().getDisplayName(TextStyle.SHORT, locale) + " the " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
                        now.getDayOfWeek().getDisplayName(textStyle, locale),
                        String.format("%02d", now.getMonthValue()) + "/" + String.format("%02d", now.getDayOfMonth()),
                        now.format(DateTimeFormatter.ISO_LOCAL_DATE),
                        String.format("%02d", now.getMonthValue()) + "/" + String.format("%02d", now.getDayOfMonth()) + "/" + now.getYear(),
                        String.format("%02d", now.getHour()) + ":" + String.format("%02d", now.getMinute())
                )
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    protected String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    private Boolean isToday(String text) {
        log.info("Testing text for todayness: " + text);
        for (String test : validCurrentTimes()) {
            if (text.equalsIgnoreCase(test)) {
                return true;
            }
        }
        log.warn("Not today: possible values were: \r\n" + validCurrentTimes().stream().map(s -> "\r\n" + s).collect(Collectors.toSet()));
        return false;
    }

    public Object getUserFirstName(Integer userId, Integer groupId) {
        ResponseEntity<FindGroupDetailsResponse> response = messenger.fetchGroupDetails(groupId);
        if (response.getBody() != null && response.getBody().getGroupDetails() != null) {
            for (GroupMember member : response.getBody().getGroupDetails().getMembers()) {
                if (member.getUserId().equals(userId)) {
                    return member.getName().split(" ")[0];
                }
            }
            return response.getBody();
        }
        return "no";
    }
}

package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.config.GroupMeConfig;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import com.hoellem.groupmebot.model.groupme.FindGroupDetailsResponse;
import com.hoellem.groupmebot.model.groupme.GroupMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayHandler implements RequestHandler
{
  private static final Pattern dayPattern = Pattern.compile("^Is (?:it|today) (\\w+)", Pattern.CASE_INSENSITIVE);
  private final GroupMeMessenger messenger;
  private final GroupMeConfig groupMeConfig;

  @Override
  public void handle(GroupMeRequest request)
  {
    Matcher matcher = dayPattern.matcher(request.getText());
    if (matcher.find())
    {
      String responseText = isToday(matcher.group(1)) ? "Yes" : "No";

      messenger.sendGroupMeMessage(responseText);
    }
  }

  private Collection<String> validCurrentTimes() {
    ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.of("-04:00"));
    TextStyle textStyle = TextStyle.FULL;
    Locale locale = Locale.US;
    return Set.of(
            "now",
            now.getYear(),
            now.getMonth().getDisplayName(textStyle, locale),
            "the " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
            now.getMonth().getDisplayName(textStyle, locale) + " " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
            now.getMonth().getDisplayName(TextStyle.SHORT, locale) + " " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
            now.getMonth().getDisplayName(textStyle, locale) + " the " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
            now.getMonth().getDisplayName(TextStyle.SHORT, locale) + " the " + now.getDayOfMonth() + getDayOfMonthSuffix(now.getDayOfMonth()),
            now.getDayOfWeek().getDisplayName(textStyle, locale)
            )
            .stream()
            .map(String::valueOf)
            .collect(Collectors.toSet());
  }

  private String getDayOfMonthSuffix(final int n) {
    if (n >= 11 && n <= 13) {
      return "th";
    }
    switch (n % 10) {
      case 1:  return "st";
      case 2:  return "nd";
      case 3:  return "rd";
      default: return "th";
    }
  }

  private Boolean isToday(String text)
  {
    for (String test : validCurrentTimes()) {
      if (text.equalsIgnoreCase(test)) {
        return true;
      }
    }
    return false;
  }

  public Object getUserFirstName(Integer userId, Integer groupId)
  {
    ResponseEntity<FindGroupDetailsResponse> response = messenger.fetchGroupDetails(groupId);
    if (response.getBody() != null && response.getBody().getGroupDetails() != null)
    {
      for (GroupMember member : response.getBody().getGroupDetails().getMembers())
      {
        if (member.getUserId().equals(userId))
        {
          return member.getName().split(" ")[0];
        }
      }
      return response.getBody();
    }
    return "no";
  }
}

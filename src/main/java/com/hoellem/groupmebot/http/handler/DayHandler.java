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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      String responseText;
      if (isValidDay(matcher.group(1)))
      {
        responseText = isToday(matcher.group(1)) ? "Yes" : "No";
      }
      else
      {
        responseText = getUserFirstName(request.getUserId(), request.getGroupId()) + ", that's not even a valid day";
      }
      messenger.sendGroupMeMessage(responseText);
    }
  }

  private Boolean isValidDay(String text)
  {
    for (String day : groupMeConfig.getDays())
    {
      if (text.equalsIgnoreCase(day))
      {
        return true;
      }
    }
    return false;
  }

  private Boolean isToday(String text)
  {
    LocalDate date = LocalDate.now(ZoneId.of("-05:00"));
    return text.equalsIgnoreCase(date.getDayOfWeek().toString());
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

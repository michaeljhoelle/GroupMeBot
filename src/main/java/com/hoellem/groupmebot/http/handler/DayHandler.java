package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import com.hoellem.groupmebot.http.groupme.getgroup.FindGroupDetailsResponse;
import com.hoellem.groupmebot.http.groupme.getgroup.GroupMember;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayHandler extends BaseHandler implements RequestHandler
{
  private static final Pattern dayPattern = Pattern.compile("^Is (?:it|today) (\\w+)", Pattern.CASE_INSENSITIVE);

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
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
    return text.equalsIgnoreCase(dateFormat.format(date));
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

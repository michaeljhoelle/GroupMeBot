package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import com.hoellem.groupmebot.http.groupme.GroupMeResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayHandler extends BaseHandler implements RequestHandler
{
  private static final Pattern parameterPattern = Pattern.compile("^Is (?:it|today) (\\w+)", Pattern.CASE_INSENSITIVE);

  @Override
  public void handle(GroupMeRequest request)
  {
    Matcher matcher = parameterPattern.matcher(request.getText());
    if (matcher.find())
    {
      String responseText;
      if (isValidDay(matcher.group(1)))
      {
        responseText = isToday(matcher.group(1)) ? "Yes" : "No";
      }
      else
      {
        responseText = groupMeApiInterface.getUserFirstName(request.getUserId(), request.getGroupId()) + ", that's not even a valid day";
      }
      GroupMeResponse groupMeResponse = new GroupMeResponse(groupMeConfig.getBotId(), responseText);
      logger.info("Posting " + groupMeResponse.toString());
      HttpEntity<GroupMeResponse> groupMePost = new HttpEntity<>(groupMeResponse, headers);
      restTemplate.exchange(botPostUrl, HttpMethod.POST, groupMePost, String.class);
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
}

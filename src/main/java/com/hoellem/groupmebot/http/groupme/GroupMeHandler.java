package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.BaseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupMeHandler extends BaseHandler
{
  private static final Pattern feetPattern = Pattern.compile("^/feet ", Pattern.MULTILINE);

  public void handle(GroupMeRequest request) {
    logger.info("Read GroupMe message from " + request.getSenderType() + " " + request.getName() + ": " + request.getText());

    if (request.senderType.equals("user"))
    {
      String responseText = processText(request.getText());
      GroupMeResponse groupMeResponse = new GroupMeResponse(botId, responseText);
      restTemplate.postForObject(url, groupMeResponse, String.class);
      logger.info("Responded with: " + responseText);
    }

  }

  private String processText(String text)
  {
    if (feetPattern.matcher(text).find())
    {
      Pattern parameterPattern = Pattern.compile(" (.+)", Pattern.MULTILINE);
      Matcher matcher = parameterPattern.matcher(text);
      if (matcher.find())
      {
        String name = matcher.group(1);
        logger.info("Parameter: " + name);
        return fetchFeetPic(name);
      }
    }
    return null;
  }

  private String fetchFeetPic(String name)
  {
    String feetUrl = "https://www.wikifeet.com/" + name.replace(" ", "_");
    logger.info("Attempting to retrieve wiki page");
    String rawHtml = (restTemplate.getForObject(feetUrl, String.class));
    List<String> pidList = new ArrayList<>();
    if (rawHtml != null)
    {
      Pattern pidPattern = Pattern.compile("\"pid\": ?(\\d+),", Pattern.MULTILINE);
      Matcher matcher = pidPattern.matcher(rawHtml);
      while (matcher.find())
      {
        pidList.add(matcher.group(1));
      }
      if (!pidList.isEmpty())
      {
        Random random = new Random();
        String pid = pidList.get(random.nextInt(pidList.size()));
        return "https://pics.wikifeet.com/" + name.replace(" ", "-") +  "-feet-" + pid + ".jpg";
      }
    }
    return null;
  }
}

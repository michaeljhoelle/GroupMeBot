package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.BaseHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
      logger.info("Posting " + groupMeResponse.toString() + " to " + url);
      HttpEntity<GroupMeResponse> groupMePost = new HttpEntity<>(groupMeResponse, headers);

      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, groupMePost, String.class);
      logger.info("Got " + response.getBody() + " back from GroupMe");
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

    HttpEntity<String> httpEntity = new HttpEntity<>(feetUrl, headers);
    RestTemplate freshRestTemplate = new RestTemplate();

    ResponseEntity<String> response = (freshRestTemplate.exchange(feetUrl, HttpMethod.GET, httpEntity, String.class));
    List<String> pidList = new ArrayList<>();
    if (response.getBody() != null)
    {
      Pattern pidPattern = Pattern.compile("\"pid\": ?(\\d+),", Pattern.MULTILINE);
      Matcher matcher = pidPattern.matcher(response.getBody());
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

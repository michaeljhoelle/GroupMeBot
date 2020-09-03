package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.AppConfig;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import com.hoellem.groupmebot.http.groupme.GroupMeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeetHandler extends BaseHandler implements RequestHandler
{
  @Override
  public void handle(GroupMeRequest request)
  {
    if (request.getSenderType().equals("user"))
    {
      String responseText = processText(request.getText());
      if (responseText != null)
      {
        GroupMeResponse groupMeResponse = new GroupMeResponse(appConfig.getId(), responseText);
        logger.info("Posting " + groupMeResponse.toString());
        HttpEntity<GroupMeResponse> groupMePost = new HttpEntity<>(groupMeResponse, headers);
        restTemplate.exchange(url, HttpMethod.POST, groupMePost, String.class);
      }
    }
  }

  private String processText(String text)
  {
    if (feetPattern.matcher(text).find())
    {
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
    HttpEntity<String> httpEntity = new HttpEntity<>(feetUrl, headers);
    RestTemplate freshRestTemplate = new RestTemplate();

    try
    {
      ResponseEntity<String> response = freshRestTemplate.exchange(feetUrl, HttpMethod.GET, httpEntity, String.class);


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
          return "https://pics.wikifeet.com/" + name.replace(" ", "-") + "-feet-" + pid + ".jpg";
        }
      }
    }
    catch (HttpClientErrorException exception)
    {
      logger.error(exception.getStatusCode().toString() + " for " + feetUrl);
    }
    return null;
  }

}

package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SufferHandler extends BaseHandler implements RequestHandler
{
  private final ArrayList<String> jpgCache = new ArrayList<>();

  public void handle(GroupMeRequest request)
  {
    sendGroupMeMessage(getImage());
  }
  private String getImage()
  {
    String sufferUrl = "https://reddit.com/r/MakeMeSuffer";
    HttpEntity<String> httpEntity = new HttpEntity<>(sufferUrl, headers);
    try
    {
      ResponseEntity<String> response = restTemplate.exchange(sufferUrl, HttpMethod.GET, httpEntity, String.class);
      if (response.getBody() != null)
      {
        List<String> jpgList = new ArrayList<>();
        Matcher matcher = Pattern.compile("i\\.redd\\.it/([^.]+).jpg").matcher(response.getBody());
        while (matcher.find())
        {
          jpgList.add(matcher.group(1));
        }

        Random random = new Random();
        while (!jpgList.isEmpty())
        {
          String jpg = jpgList.get(random.nextInt(jpgList.size()));
          if (jpgCache.contains(jpg))
          {
            jpgList.remove(jpg);
            jpgCache.remove(jpg);
            jpgCache.add(0, jpg);
          }
          else
          {
            jpgCache.add(jpg);
            if (jpgCache.size() > 14)
            {
              jpgCache.remove(jpgCache.size()-1);
            }
            return "https://i.redd.it/" + jpg + ".jpg";
          }
        }
        return "No fresh suffering available right now";
      }
    }
    catch (HttpClientErrorException exception)
    {
      logger.error(exception.getStatusCode().toString() + " for " + sufferUrl);
    }
    return null;
  }
}

package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeetHandler extends BaseHandler implements RequestHandler
{
  private static final Pattern parameterPattern = Pattern.compile(" (.+)", Pattern.MULTILINE);

  @Override
  public void handle(GroupMeRequest request)
  {
    String responseText = generateResponse(request.getText());
    String fullResponse = responseText != null ? responseText : "No results ¯\\_(ツ)_/¯";
    messenger.sendGroupMeMessage(fullResponse);
  }

  private String generateResponse(String text)
  {
    Matcher matcher = parameterPattern.matcher(text);
    String response = null, name;
    if (matcher.find())
    {
      name = matcher.group(1);
      logger.info("Parameter: " + name);
      if (name.equalsIgnoreCase("top") || name.equalsIgnoreCase("popular"))
      {
        Random random = new Random();
        int page = (random.nextInt(20));
        name = searchPeople("https://www.wikifeet.com/celebs?sort=most&page=" + page, true);
      }
      else
      {
        response = fetchFeetPic(name);
        if (response == null)
        {
          name = searchPeople("https://www.wikifeet.com/search/" + name.replace(" ", "%20"), false);
        }
      }
    }
    else
    {
      Random random = new Random();
      int page = (random.nextInt(3828));
      name = searchPeople("https://www.wikifeet.com/celebs?page=" + page, true);
    }
    if (name != null)
    {
      response = fetchFeetPic(name);
    }
    return response;
  }

  private String fetchFeetPic(String name)
  {
    String feetUrl = "https://www.wikifeet.com/" + name.replace(" ", "_");
    HttpEntity<String> httpEntity = new HttpEntity<>(feetUrl, headers);

    try
    {
      ResponseEntity<String> response = restTemplate.exchange(feetUrl, HttpMethod.GET, httpEntity, String.class);
      if (response.getBody() != null)
      {
        List<String> pidList = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"pid\": ?(\\d+),", Pattern.MULTILINE).matcher(response.getBody());
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

  private String searchPeople(String url, boolean randomSearch)
  {
    HttpEntity<String> httpEntity = new HttpEntity<>(url, headers);

    try
    {
      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
      if (response.getBody() != null)
      {
        Matcher matcher = Pattern.compile("a href=\"/([^\"]+)\"", Pattern.MULTILINE).matcher(response.getBody());
        if (randomSearch)
        {
          ArrayList<String> personList = new ArrayList<>();
          while (matcher.find())
          {
            personList.add(matcher.group(1));
          }
          Random random = new Random();
          return personList.get(random.nextInt(personList.size()));
        }
        if (matcher.find())
        {
          return matcher.group(1);
        }
      }
    }
    catch (HttpClientErrorException exception)
    {
      logger.error(exception.getStatusCode().toString() + " for " + url);
    }
    return null;
  }

}

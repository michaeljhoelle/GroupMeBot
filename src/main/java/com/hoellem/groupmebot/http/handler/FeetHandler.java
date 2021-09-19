package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import com.hoellem.groupmebot.repository.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class FeetHandler implements RequestHandler
{
  private static final Pattern feetCountPattern = Pattern.compile("^/feetcount\\b", Pattern.CASE_INSENSITIVE);
  private static final HttpHeaders headers = new HttpHeaders();
  private final GroupMeMessenger messenger;
  private final UserService userService;
  private final RestTemplate restTemplate = new RestTemplate();

  public FeetHandler(GroupMeMessenger messenger, UserService userService) {
    this.messenger = messenger;
    this.userService = userService;
    headers.put("user-agent", Collections.singletonList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"));
  }

  @Override
  public void handle(GroupMeRequest request)
  {
    String responseText = generateResponse(request.getText(), request.getUserId());
    String fullResponse = responseText != null ? responseText : "No results ¯\\_(ツ)_/¯";
    messenger.sendGroupMeMessage(fullResponse);
  }

  private String generateResponse(String text, Integer userId)
  {
    Matcher matcher;
    matcher = feetCountPattern.matcher(text);
    if (matcher.find())
    {
      return userService.getFeetCounts();
    }
    userService.incrementFeetCount(userId);

    matcher = parameterPattern.matcher(text);
    String response = null, name;
    if (matcher.find())
    {
      name = matcher.group(1);
      log.info("Parameter: " + name);
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
      log.error(exception.getStatusCode() + " for " + feetUrl);
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
      log.error(exception.getStatusCode() + " for " + url);
    }
    return null;
  }

}

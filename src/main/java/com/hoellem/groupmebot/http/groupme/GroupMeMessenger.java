package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.groupme.getgroup.FindAllGroupsResponse;
import com.hoellem.groupmebot.http.groupme.getgroup.FindGroupDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class GroupMeMessenger
{
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  private final HttpHeaders headers;
  private final RestTemplate restTemplate;
  private final GroupMeConfig groupMeConfig;
  private static final String baseUrl = "https://api.groupme.com/v3";
  private static final String groupsPath = "/groups";
  private static final String postPath = "/bots/post";

  @Autowired
  public GroupMeMessenger(GroupMeConfig groupMeConfig, RestTemplate restTemplate)
  {
    this.restTemplate = restTemplate;
    this.groupMeConfig = groupMeConfig;
    headers = new HttpHeaders();
    headers.put("user-agent", Collections.singletonList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"));
    headers.put("X-Access-Token", Collections.singletonList(groupMeConfig.getAccessToken()));
  }

  public void sendGroupMeMessage(String text)
  {
    if (text != null)
    {
      GroupMeBotResponse groupMeBotResponse = new GroupMeBotResponse(groupMeConfig.getBotId(), text);
      HttpEntity<GroupMeBotResponse> groupMePost = new HttpEntity<>(groupMeBotResponse, headers);
      restTemplate.exchange(baseUrl + postPath, HttpMethod.POST, groupMePost, String.class);
      logger.info("Posted " + groupMeBotResponse.toString());
    }
  }

  public ResponseEntity<FindGroupDetailsResponse> fetchGroupDetails(Integer groupId)
  {
    HttpEntity<String> httpEntity = new HttpEntity<>(baseUrl + groupsPath, headers);
    return restTemplate.exchange(baseUrl + groupsPath + "/" + groupId, HttpMethod.GET, httpEntity, FindGroupDetailsResponse.class);
  }

  public ResponseEntity<FindAllGroupsResponse> fetchAllGroupDetails(int page)
  {
    HttpEntity<String> httpEntity = new HttpEntity<>(baseUrl + groupsPath, headers);
    return restTemplate.exchange(baseUrl + groupsPath + "?page=" + page, HttpMethod.GET, httpEntity, FindAllGroupsResponse.class);
  }
}

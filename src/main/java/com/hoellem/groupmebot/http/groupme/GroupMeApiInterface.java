package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.groupme.getgroup.FindGroupDetailsResponse;
import com.hoellem.groupmebot.http.groupme.getgroup.GroupDetails;
import com.hoellem.groupmebot.http.groupme.getgroup.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class GroupMeApiInterface
{
  private final HttpHeaders headers;
  private final RestTemplate restTemplate;

  @Autowired
  public GroupMeApiInterface(GroupMeConfig groupMeConfig, RestTemplate restTemplate)
  {
    this.restTemplate = restTemplate;
    headers = new HttpHeaders();
    headers.put("user-agent", Collections.singletonList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"));
    headers.put("X-Access-Token", Collections.singletonList(groupMeConfig.getAccessToken()));
  }

  public Object getUserFirstName(String userId, String groupId)
  {
    String url = "https://api.groupme.com/v3/groups/" + groupId;
    HttpEntity<String> httpEntity = new HttpEntity<>(url, headers);
    ResponseEntity<FindGroupDetailsResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, FindGroupDetailsResponse.class);
    if (response.getBody() != null && response.getBody().getGroupDetails() != null)
    {
      for (GroupMember member : response.getBody().getGroupDetails().getMembers())
      {
        if (member.getUserId().equalsIgnoreCase(userId))
        {
          return member.getName().split(" ")[0];
        }
      }
      return response.getBody();
    }
    return "no";
  }
}

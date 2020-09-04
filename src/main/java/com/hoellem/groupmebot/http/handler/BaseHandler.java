package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.groupme.GroupMeApiInterface;
import com.hoellem.groupmebot.http.groupme.GroupMeConfig;
import com.hoellem.groupmebot.http.groupme.GroupMeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class BaseHandler
{
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  protected static HttpHeaders headers;
  protected GroupMeConfig groupMeConfig;
  protected RestTemplate restTemplate;
  protected GroupMeApiInterface groupMeApiInterface;

  protected static final String botPostUrl = "https://api.groupme.com/v3/bots/post";

  public BaseHandler()
  {
    headers = new HttpHeaders();
    headers.put("user-agent", Collections.singletonList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"));
  }

  @Autowired
  public void setGroupMeApiInterface(GroupMeApiInterface groupMeApiInterface)
  {
    this.groupMeApiInterface = groupMeApiInterface;
  }

  @Autowired
  public void setAppConfig(GroupMeConfig groupMeConfig)
  {
    this.groupMeConfig = groupMeConfig;
  }

  @Autowired
  public void setRestTemplate(RestTemplate restTemplate)
  {
    this.restTemplate = restTemplate;
  }

  protected void sendGroupMeMessage(String text)
  {
    if (text != null)
    {
      GroupMeResponse groupMeResponse = new GroupMeResponse(groupMeConfig.getBotId(), text);
      HttpEntity<GroupMeResponse> groupMePost = new HttpEntity<>(groupMeResponse, headers);
      restTemplate.exchange(botPostUrl, HttpMethod.POST, groupMePost, String.class);
      logger.info("Posted " + groupMeResponse.toString());
    }
  }

}

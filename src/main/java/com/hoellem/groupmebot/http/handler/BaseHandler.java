package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.datasource.user.UserService;
import com.hoellem.groupmebot.http.groupme.GroupMeMessenger;
import com.hoellem.groupmebot.http.groupme.GroupMeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.regex.Pattern;

@Component
public class BaseHandler
{
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  protected static final Pattern parameterPattern = Pattern.compile(" (.+)", Pattern.MULTILINE);
  protected static final Pattern requestPattern = Pattern.compile("^/(\\w+)\\b", Pattern.MULTILINE);
  protected static HttpHeaders headers;
  protected GroupMeConfig groupMeConfig;
  protected RestTemplate restTemplate;
  protected UserService userService;
  protected GroupMeMessenger messenger;

  public BaseHandler()
  {
    headers = new HttpHeaders();
    headers.put("user-agent", Collections.singletonList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"));
  }

  @Autowired
  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }

  @Autowired
  public void setGroupMeApiInterface(GroupMeMessenger groupMeMessenger)
  {
    this.messenger = groupMeMessenger;
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

}

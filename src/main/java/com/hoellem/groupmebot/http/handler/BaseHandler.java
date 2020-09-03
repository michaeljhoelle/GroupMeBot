package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.GroupMeBotApplication;
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
  protected static HttpHeaders headers;
  protected GroupMeConfig groupMeConfig;
  protected RestTemplate restTemplate;

  protected static final String url = "https://api.groupme.com/v3/bots/post";
  protected static final Pattern parameterPattern = Pattern.compile(" (.+)", Pattern.MULTILINE);

  public BaseHandler()
  {
    headers = new HttpHeaders();
    headers.put("user-agent", Collections.singletonList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"));
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

   public HttpHeaders getHeaders()
   {
     return headers;
   }

}

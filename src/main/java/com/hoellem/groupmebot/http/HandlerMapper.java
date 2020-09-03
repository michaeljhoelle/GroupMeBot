package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.GroupMeBotApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HandlerMapper
{
  private Map<String, RequestHandler> handlerMap;
  protected static final String url = "https://api.groupme.com/v3/bots/post";
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  private HttpHeaders headers;
  private RestTemplate restTemplate;
  private GroupMeConfig config;
  private HandlerConfig handlerConfig;

  @Autowired
  public void setHandlerMapper(HandlerConfig handlerConfig)
  {
    this.handlerMap = handlerConfig.getHandlerMap();
  }

  @Autowired
  public void setRestTemplate(RestTemplate restTemplate)
  {
    this.restTemplate = restTemplate;
  }

  @Autowired
  public void setConfig(GroupMeConfig config)
  {
    this.config = config;
  }

  public Map<String, RequestHandler> getHandlers()
  {
    return handlerMap;
  }

  public String handle()
  {
    return "Absolutely";
  }
}

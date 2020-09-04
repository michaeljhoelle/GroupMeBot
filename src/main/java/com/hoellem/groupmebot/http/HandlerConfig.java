package com.hoellem.groupmebot.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlerConfig
{
  private static Map<String, RequestHandler> handlerMap;

  @Autowired
  public HandlerConfig(HandlerBuilder handlerBuilder)
  {
    handlerMap = new HashMap<>();
    handlerMap.put("^/feet\\b", handlerBuilder.feetHandler());
    handlerMap.put("^/cls3\\b", handlerBuilder.clsHandler());
    handlerMap.put("^is (it|today) ", handlerBuilder.dayHandler());
  }

  public Map<String, RequestHandler> getHandlerMap()
  {
    return handlerMap;
  }


}

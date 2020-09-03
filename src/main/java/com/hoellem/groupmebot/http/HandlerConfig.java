package com.hoellem.groupmebot.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlerConfig
{
  private HandlerBuilder handlerBuilder;
  private static Map<String, RequestHandler> handlerMap;

  @Autowired
  public HandlerConfig(HandlerBuilder handlerBuilder)
  {
    this.handlerBuilder = handlerBuilder;
    handlerMap = new HashMap<>();
    handlerMap.put("/feet", handlerBuilder.feetHandler());
    handlerMap.put("/cls3", handlerBuilder.clsHandler());
  }

  public Map<String, RequestHandler> getHandlerMap()
  {
    return handlerMap;
  }


}

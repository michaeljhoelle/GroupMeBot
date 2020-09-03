package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.http.handler.ClsHandler;
import com.hoellem.groupmebot.http.handler.FeetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerBuilder
{
  @Bean
  public FeetHandler feetHandler()
  {
    return new FeetHandler();
  }

  @Bean
  public ClsHandler clsHandler()
  {
    return new ClsHandler();
  }
}

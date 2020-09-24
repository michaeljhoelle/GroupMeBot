package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.http.handler.*;
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

  @Bean
  public DayHandler dayHandler()
  {
    return new DayHandler();
  }

  @Bean
  SufferHandler sufferHandler()
  {
    return new SufferHandler();
  }

  @Bean
  TodoHandler todoHandler()
  {
    return new TodoHandler();
  }
}

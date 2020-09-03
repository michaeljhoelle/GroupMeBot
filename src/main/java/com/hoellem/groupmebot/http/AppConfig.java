package com.hoellem.groupmebot.http;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "bot")
public class AppConfig
{
  private String id;

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @Bean
  public RestTemplate restTemplate()
  {
    return new RestTemplate();
  }
}

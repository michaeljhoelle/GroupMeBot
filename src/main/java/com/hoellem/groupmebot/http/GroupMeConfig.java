package com.hoellem.groupmebot.http;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "groupme")
public class GroupMeConfig
{
  private String botId;

  public String getBotId()
  {
    return botId;
  }

  public void setBotId(String botId)
  {
    this.botId = botId;
  }

  @Bean
  public RestTemplate restTemplate()
  {
    return new RestTemplate();
  }
}

package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.groupme.getgroup.FindGroupDetailsResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "groupme")
public class GroupMeConfig
{
  private String accessToken;
  private String botId;
  private String[] days;

  public String getAccessToken()
  {
    return accessToken;
  }

  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
  }

  public String getBotId()
  {
    return botId;
  }

  public void setBotId(String botId)
  {
    this.botId = botId;
  }

  public String[] getDays()
  {
    return days;
  }

  public void setDays(String[] days)
  {
    this.days = days;
  }

  @Bean
  public RestTemplate restTemplate()
  {
    return new RestTemplate();
  }

  @Bean
  public FindGroupDetailsResponse getGroupResponse()
  {
    return new FindGroupDetailsResponse();
  }
}

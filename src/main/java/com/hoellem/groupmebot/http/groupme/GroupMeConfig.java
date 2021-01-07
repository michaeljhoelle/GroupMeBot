package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.groupme.getgroup.FindGroupDetailsResponse;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "groupme")
@Data
public class GroupMeConfig
{
  private String accessToken;
  private String botId;
  private String[] days;
  private String userAgent;

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

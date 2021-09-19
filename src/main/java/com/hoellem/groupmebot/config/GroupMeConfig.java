package com.hoellem.groupmebot.config;

import com.hoellem.groupmebot.model.groupme.FindGroupDetailsResponse;
import feign.form.spring.SpringFormEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "groupme")
public class GroupMeConfig extends WebSecurityConfigurerAdapter
{
  private String accessToken;
  private String botId;
  private String[] days;
  private String userAgent;

  @Bean
  SpringFormEncoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
    return new SpringFormEncoder(new SpringEncoder(converters));
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

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
            .antMatchers("/*").permitAll()
            .anyRequest().authenticated();
  }
}

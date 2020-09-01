package com.hoellem.diceroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotPoster
{
  private static final String url = "https://api.groupme.com/v3/bots/post";
  private static final String botId = "18751eb77b6a71d764ddfd1101";
  private static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  private final RestTemplate restTemplate;

  public BotPoster() {
    this.restTemplate = new RestTemplate();
  }

  public void postJSON() {
    BotRequest request = new BotRequest(botId, "Test Text");
    String response = restTemplate.postForObject(url, request, String.class);
    if (response != null)
    {
      logger.info(response);
    }
  }
}

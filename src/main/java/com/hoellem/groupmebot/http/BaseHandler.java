package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.BaseRequest;
import com.hoellem.groupmebot.GroupMeBotApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseHandler
{
  protected static final String url = "https://api.groupme.com/v3/bots/post";
  protected static final String botId = "18751eb77b6a71d764ddfd1101";
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  protected final RestTemplate restTemplate;

  public BaseHandler() {
    this.restTemplate = new RestTemplate();
  }

  public void handle(BaseRequest request) {
    String response = restTemplate.postForObject(url, request, String.class);
    if (response != null)
    {
      logger.info(response);
    }
  }
}

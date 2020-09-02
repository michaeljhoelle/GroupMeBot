package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.BaseRequest;
import com.hoellem.groupmebot.GroupMeBotApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseHandler
{
  protected static final String url = "https://api.groupme.com/v3/bots/post";
  protected static final String botId = "7db4c25232ca10324e5935b037";
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  protected final HttpHeaders headers;
  protected final RestTemplate restTemplate;

  public BaseHandler() {
    this.restTemplate = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
  }

  public void handle(BaseRequest request) {
    String response = restTemplate.postForObject(url, request, String.class);
    if (response != null)
    {
      logger.info(response);
    }
  }
}

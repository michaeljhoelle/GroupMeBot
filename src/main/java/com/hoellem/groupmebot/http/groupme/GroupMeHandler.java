package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.BaseHandler;

public class GroupMeHandler extends BaseHandler
{
  public void handle(GroupMeRequest request) {
    String response = restTemplate.postForObject(url, request.getAvatar(), String.class);
    if (response != null)
    {
      logger.info(response);
    }
  }
}

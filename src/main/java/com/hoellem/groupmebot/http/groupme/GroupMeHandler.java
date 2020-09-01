package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.BaseHandler;

public class GroupMeHandler extends BaseHandler
{
  public void handle(GroupMeRequest request) {
    GroupMeResponse groupMeResponse = new GroupMeResponse(botId, request.getAvatar());
    String finalResponse = restTemplate.postForObject(url, groupMeResponse, String.class);
    if (finalResponse != null)
    {
      logger.info(finalResponse);
    }
  }
}

package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.BaseHandler;

public class GroupMeHandler extends BaseHandler
{
  public void handle(GroupMeRequest request) {
    logger.info("Read GroupMe message from + " + request.getSenderType() + " " + request.getName() + ": " + request.getText());

    if (request.senderType.equals("user"))
    {
      String responseText = "Generic Response Text";
      GroupMeResponse groupMeResponse = new GroupMeResponse(botId, request.getText());
      restTemplate.postForObject(url, groupMeResponse, String.class);
      logger.info("Responded with: " + responseText);
    }

  }

}

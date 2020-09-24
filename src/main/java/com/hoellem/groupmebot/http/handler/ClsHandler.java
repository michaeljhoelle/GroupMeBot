package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;

public class ClsHandler extends BaseHandler implements RequestHandler
{
  @Override
  public void handle(GroupMeRequest request)
  {
    groupMeMessenger.sendGroupMeMessage(fetchClearingText());
  }

  private String fetchClearingText()
  {
    return "A".repeat(15).concat("\n").repeat(60);
  }
}

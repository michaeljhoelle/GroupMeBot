package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClsHandler implements RequestHandler
{
  private final GroupMeMessenger messenger;

  @Override
  public void handle(GroupMeRequest request)
  {
    messenger.sendGroupMeMessage(fetchClearingText());
  }

  private String fetchClearingText()
  {
    return "A".repeat(15).concat("\n").repeat(60);
  }
}

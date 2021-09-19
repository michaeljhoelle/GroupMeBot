package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubredditHandler implements RequestHandler
{
  private final GroupMeMessenger messenger;

  @Override
  public void handle(GroupMeRequest request)
  {
    messenger.sendGroupMeMessage(constructUrl(request.getText()));
  }

  private String constructUrl(String subreddit)
  {
    return "https://www.reddit.com/" + subreddit;
  }
}

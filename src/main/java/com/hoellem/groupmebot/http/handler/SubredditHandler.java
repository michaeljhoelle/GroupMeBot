package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;

public class SubredditHandler extends BaseHandler implements RequestHandler
{
  @Override
  public void handle(GroupMeRequest request)
  {
    messenger.sendGroupMeMessage(cookUrl(request.getText()));
  }

  private String cookUrl(String subreddit)
  {
    return "https://www.reddit.com/" + subreddit;
  }
}

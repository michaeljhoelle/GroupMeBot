package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.http.groupme.GroupMeRequest;

public interface RequestHandler
{
  void handle(GroupMeRequest request);
}

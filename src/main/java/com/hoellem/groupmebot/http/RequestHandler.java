package com.hoellem.groupmebot.http;

import com.hoellem.groupmebot.model.groupme.GroupMeRequest;

import java.util.regex.Pattern;

public interface RequestHandler
{
  Pattern parameterPattern = Pattern.compile(" (.+)", Pattern.MULTILINE);
  Pattern requestPattern = Pattern.compile("^/(\\w+)\\b", Pattern.MULTILINE);

  void handle(GroupMeRequest request);
}

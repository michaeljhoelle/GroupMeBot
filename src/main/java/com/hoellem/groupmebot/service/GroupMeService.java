package com.hoellem.groupmebot.service;

import com.hoellem.groupmebot.client.RedditClient;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.handler.*;
import com.hoellem.groupmebot.model.groupme.Command;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class GroupMeService implements RequestHandler
{
  private final RedditClient redditClient;
  private final ClsHandler clsHandler;
  private final DayHandler dayHandler;
  private final FeetHandler feetHandler;
  private final SubredditHandler subredditHandler;
  private final SubredditPostService subredditPostService;
  private final TodoHandler todoHandler;

  public void handle(GroupMeRequest request) {
    if (GroupMeRequest.SenderType.USER.equals(request.getSenderType())) {
      for (Command command : Command.values()) {
        command.getPatterns().forEach(p -> {
          Matcher matcher = Pattern.compile(p, Pattern.CASE_INSENSITIVE).matcher(request.getText());
          if (matcher.find()) {
            executeCommand(command, request);
          }
        });
      }
    }
  }

  private void executeCommand(Command command, GroupMeRequest request) {
    RequestHandler handler;
    switch (command) {
      case CLS:
        handler = clsHandler;
        break;
      case TODAY:
        handler = dayHandler;
        break;
      case FEET:
        handler = feetHandler;
        break;
      case SUBREDDIT_FIND:
        handler = subredditHandler;
        break;
      case SUBREDDIT_POST:
        handler = subredditPostService;
        break;
      case TODO:
        handler = todoHandler;
        break;
      default:
        return;
    }
    handler.handle(request);
  }

  public void testRedditApi() {
    log.debug(redditClient.getHotSuffering().toString());
  }
}

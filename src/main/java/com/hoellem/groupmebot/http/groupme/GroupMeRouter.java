package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.HandlerConfig;
import com.hoellem.groupmebot.http.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GroupMeRouter
{
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);

  private final HandlerConfig handlerConfig;

  public GroupMeRouter(HandlerConfig handlerConfig)
  {
    this.handlerConfig = handlerConfig;
  }

  public void handle(GroupMeRequest request) {
    if (request.getSenderType().equals("user"))
    {
      RequestHandler handler = getHandler(request.getText());
      if (handler != null)
      {
        logger.info("\n" + request.getName() + ": " + request.getText());
        handler.handle(request);
      }
    }
  }

  private RequestHandler getHandler(String text)
  {
    Matcher matcher;
    for (Map.Entry<String, RequestHandler> set : handlerConfig.getHandlerMap().entrySet())
    {
      matcher = Pattern.compile(set.getKey(), Pattern.CASE_INSENSITIVE).matcher(text);
      if (matcher.find())
      {
        return set.getValue();
      }
    }
    return null;
  }
}

package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.HandlerMapper;
import com.hoellem.groupmebot.http.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GroupMeRouter
{
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  private HandlerMapper mapper;
  private GroupMeConfig groupMeConfig;
  private RestTemplate restTemplate;

  @Autowired
  public void setGroupMeConfig(GroupMeConfig groupMeConfig)
  {
    this.groupMeConfig = groupMeConfig;
  }

  @Autowired
  public void setMapper(HandlerMapper mapper)
  {
    this.mapper = mapper;
  }

  public void handle(GroupMeRequest request) {
    logger.info("Read GroupMe message from " + request.getSenderType() + " " + request.getName() + ": " + request.getText());
    String command = parseCommand(request.getText());
    RequestHandler handler = mapper.getHandlers().get(command);
    if (handler != null)
    {
      handler.handle(request);
    }
  }

  private String parseCommand(String text)
  {
    Matcher matcher = Pattern.compile("^(/\\w+)\\b", Pattern.MULTILINE).matcher(text);
    if (matcher.find())
    {
      return matcher.group();
    }
    return null;
  }
}

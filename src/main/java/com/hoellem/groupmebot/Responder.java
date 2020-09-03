package com.hoellem.groupmebot;

import com.hoellem.groupmebot.http.HandlerMapper;
import com.hoellem.groupmebot.http.groupme.GroupMeRouter;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class Responder
{
  private HandlerMapper handlerMapper;
  private GroupMeRouter groupMeRouter;

  @Autowired
  private Responder(HandlerMapper handlerMapper)
  {
    this.handlerMapper = handlerMapper;
  }

  @Autowired
  public void setHandlerMapper(HandlerMapper handlerMapper)
  {
    this.handlerMapper = handlerMapper;
  }

  @Autowired
  public void setGroupMeHandler(GroupMeRouter groupMeRouter)
  {
    this.groupMeRouter = groupMeRouter;
  }

  private static final Pattern pattern = Pattern.compile("^/\\w+", Pattern.MULTILINE);

  @GetMapping("/error")
  public ErrorResponse error()
  {
    return new ErrorResponse();
  }

  @GetMapping("/testing")
  public String test()
  {
    return handlerMapper.handle();
  }

  @PostMapping("/groupme")
  public void groupMePost(@RequestBody GroupMeRequest request)
  {
    if (pattern.matcher(request.getText()).find())
    {
      groupMeRouter.handle(request);
    }
  }
}

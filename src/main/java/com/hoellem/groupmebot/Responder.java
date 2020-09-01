package com.hoellem.groupmebot;

import com.hoellem.groupmebot.http.BaseHandlerResponse;
import com.hoellem.groupmebot.http.groupme.GroupMeHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Responder
{
  private static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);

  @GetMapping("/error")
  public ErrorResponse error()
  {
    return new ErrorResponse();
  }

  @PostMapping("/groupme")
  public BaseHandlerResponse groupMePost(@RequestBody GroupMeRequest request)
  {
    logger.info("Responding to GroupMe");
    GroupMeHandler handler = new GroupMeHandler();
    handler.handle(request);
    return new BaseHandlerResponse("OK");
  }
}

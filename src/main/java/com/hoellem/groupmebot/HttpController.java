package com.hoellem.groupmebot;

import com.hoellem.groupmebot.http.groupme.GroupMeRouter;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class HttpController
{
  private GroupMeRouter groupMeRouter;

  @Autowired
  public void setGroupMeHandler(GroupMeRouter groupMeRouter)
  {
    this.groupMeRouter = groupMeRouter;
  }

  @GetMapping("/error")
  public ErrorResponse error()
  {
    return new ErrorResponse();
  }

  @PostMapping("/groupme")
  public void groupMePost(@RequestBody GroupMeRequest request)
  {
    groupMeRouter.handle(request);
  }
}

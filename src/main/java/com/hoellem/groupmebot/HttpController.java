package com.hoellem.groupmebot;

import com.hoellem.groupmebot.http.groupme.GroupMeRouter;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<HttpStatus> groupMePost(@RequestBody GroupMeRequest request)
  {
    groupMeRouter.handle(request);
    return ResponseEntity.ok(HttpStatus.OK);
  }
}

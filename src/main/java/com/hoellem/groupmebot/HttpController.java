package com.hoellem.groupmebot;

import com.hoellem.groupmebot.http.client.GroupMeClient;
import com.hoellem.groupmebot.http.groupme.GroupMeConfig;
import com.hoellem.groupmebot.http.groupme.GroupMeRouter;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import com.hoellem.groupmebot.http.groupme.GroupMeUserResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@RestController
@RequiredArgsConstructor
public class HttpController
{
  private final GroupMeRouter groupMeRouter;
  private final GroupMeClient groupMeClient;
  private final GroupMeConfig groupMeConfig;
  private static final Pattern duckPattern = Pattern.compile("\\\\.*_.*<", Pattern.MULTILINE);

  @GetMapping("/error")
  public ErrorResponse error()
  {
    return new ErrorResponse();
  }

  @PostMapping("/groupme")
  @ResponseStatus(HttpStatus.OK)
  public void groupMePost(@RequestBody GroupMeRequest request)
  {
    Matcher matcher = duckPattern.matcher(request.getText());
    if (request.getSenderType() == GroupMeRequest.SenderType.BOT && matcher.find()) {
      GroupMeUserResponse body = new GroupMeUserResponse(UUID.randomUUID().toString(), "/bang");
      groupMeClient.sendMessage(groupMeConfig.getAccessToken(), groupMeConfig.getUserAgent(), request.getGroupId(), body);
    }
    groupMeRouter.handle(request);
  }
}

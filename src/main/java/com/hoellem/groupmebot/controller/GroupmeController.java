package com.hoellem.groupmebot.controller;

import com.hoellem.groupmebot.ErrorResponse;
import com.hoellem.groupmebot.client.GroupMeClient;
import com.hoellem.groupmebot.config.GroupMeConfig;
import com.hoellem.groupmebot.service.GroupMeService;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.regex.Pattern;

@Data
@RestController
@RequiredArgsConstructor
public class GroupmeController
{
  private final GroupMeService groupMeService;
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
    /*
    Matcher matcher = duckPattern.matcher(request.getText());
    if (request.getSenderType() == GroupMeRequest.SenderType.BOT && matcher.find()) {
      GroupMeUserResponse body = new GroupMeUserResponse(UUID.randomUUID().toString(), "/bang");
      groupMeClient.sendMessage(groupMeConfig.getAccessToken(), groupMeConfig.getUserAgent(), request.getGroupId(), body);
    }
    */
    groupMeService.handle(request);
  }

  @GetMapping
  public Set<String> test() {
    return groupMeService.testRedditApi();
  }
}

package com.hoellem.groupmebot.http.client;

import com.hoellem.groupmebot.http.groupme.GroupMeUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "groupeme", url = "https://api.groupme.com/v3")
public interface GroupMeClient
{
  @PostMapping(
      value = "/groups/{groupID}/messages",
      params = "token")
  void sendMessage(
      @RequestParam("token") String accessToken,
      @RequestHeader("user-agent") String userAgent,
      @PathVariable Integer groupID,
      @RequestBody GroupMeUserResponse body);
}
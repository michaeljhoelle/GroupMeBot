package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import com.hoellem.groupmebot.http.groupme.GroupMeResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class ClsHandler extends BaseHandler implements RequestHandler
{
  @Override
  public void handle(GroupMeRequest request)
  {
    GroupMeResponse groupMeResponse = new GroupMeResponse(groupMeConfig.getBotId(), fetchClearingText());
    logger.info("Posting " + groupMeResponse.toString());
    HttpEntity<GroupMeResponse> groupMePost = new HttpEntity<>(groupMeResponse, headers);
    restTemplate.exchange(url, HttpMethod.POST, groupMePost, String.class);
  }

  private String fetchClearingText()
  {
    return "A".repeat(10).concat("\n").repeat(80);
  }
}

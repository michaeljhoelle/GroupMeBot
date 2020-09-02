package com.hoellem.groupmebot.http.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupMeResponse
{
  private final String botId;
  private final String text;

  public GroupMeResponse(String botId, String text)
  {
    this.botId = botId;
    this.text = text;
  }

  @JsonProperty("bot_id")
  public String getBotId()
  {
    return botId;
  }

  @JsonProperty("text")
  public String getText()
  {
    return text;
  }

  public String toString()
  {
    return "{bot_id:" + botId + ", text:" + text + "}";
  }

}

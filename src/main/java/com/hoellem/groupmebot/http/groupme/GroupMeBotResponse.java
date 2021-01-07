package com.hoellem.groupmebot.http.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Data
@RequiredArgsConstructor
public class GroupMeBotResponse
{
  @JsonProperty("bot_id")
  private final String botId;
  @JsonProperty("text")
  private final String text;

}

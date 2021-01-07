package com.hoellem.groupmebot.http.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class GroupMeRequest
{
  @JsonProperty("name")
  private String name;
  @JsonProperty("text")
  private String text;
  @JsonProperty("sender_type")
  private SenderType senderType;
  @JsonProperty("group_id")
  private Integer groupId;
  @JsonProperty("user_id")
  private Integer userId;

  @RequiredArgsConstructor
  public enum SenderType
  {
    BOT("bot"),
    USER("user");

    private final String name;
    @Override
    public String toString()
    {
      return name;
    }
  }
}

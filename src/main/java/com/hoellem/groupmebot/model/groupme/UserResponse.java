package com.hoellem.groupmebot.model.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@JsonRootName(value = "message")
public class UserResponse
{
  @JsonProperty("source_guid")
  private final String guid;
  @JsonProperty("text")
  private final String text;
}

package com.hoellem.groupmebot.http.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GroupMeRequest
{
  private String name;
  private String text;
  private String senderType;

  public GroupMeRequest(){}

  @JsonProperty("name")
  public String getName()
  {
    return this.name;
  }

  @JsonProperty("name")
  public void setName(String name)
  {
    this.name = name;
  }

  @JsonProperty("text")
  public String getText()
  {
    return this.text;
  }

  @JsonProperty("text")
  public void setText(String text)
  {
    this.text = text;
  }

  @JsonProperty("sender_type")
  public String getSenderType()
  {
    return this.senderType;
  }

  @JsonProperty("sender_type")
  public void setSenderType(String senderType)
  {
    this.senderType = senderType;
  }
}

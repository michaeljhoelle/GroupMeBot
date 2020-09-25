package com.hoellem.groupmebot.http.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupMeRequest
{
  @JsonProperty("name")
  private String name;
  @JsonProperty("text")
  private String text;
  @JsonProperty("sender_type")
  private String senderType;
  @JsonProperty("group_id")
  private Integer groupId;
  @JsonProperty("user_id")
  private Integer userId;

  public GroupMeRequest(){}

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getText()
  {
    return this.text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public String getSenderType()
  {
    return this.senderType;
  }

  public void setSenderType(String senderType)
  {
    this.senderType = senderType;
  }

  public Integer getGroupId()
  {
    return this.groupId;
  }

  public void setGroupId(Integer groupId)
  {
    this.groupId = groupId;
  }

  public Integer getUserId()
  {
    return userId;
  }

  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
}

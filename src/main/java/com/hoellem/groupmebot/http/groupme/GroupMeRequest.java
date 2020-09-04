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
  private String groupId;
  @JsonProperty("user_id")
  private String userId;

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

  public String getGroupId()
  {
    return this.groupId;
  }

  public void setGroupId(String groupId)
  {
    this.groupId = groupId;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }
}

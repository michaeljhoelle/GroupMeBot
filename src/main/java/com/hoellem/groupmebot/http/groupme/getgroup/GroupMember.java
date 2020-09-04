package com.hoellem.groupmebot.http.groupme.getgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupMember
{
  @JsonProperty("user_id")
  private String userId;
  @JsonProperty("name")
  private String name;

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}

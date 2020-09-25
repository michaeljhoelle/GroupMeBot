package com.hoellem.groupmebot.http.groupme.getgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupMember
{
  @JsonProperty("user_id")
  private Integer userId;
  @JsonProperty("name")
  private String name;
  @JsonProperty("nickname")
  private String nickname;

  public Integer getUserId()
  {
    return userId;
  }

  public void setUserId(Integer userId)
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

  public String getNickname()
  {
    return nickname;
  }

  public void setNickname(String nickname)
  {
    this.nickname = nickname;
  }
}

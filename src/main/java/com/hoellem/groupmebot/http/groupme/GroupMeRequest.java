package com.hoellem.groupmebot.http.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GroupMeRequest
{
  public List<Object> attachments;
  public String avatar;
  public String text;

  public GroupMeRequest(){}

  @JsonProperty("attachments")
  public List<Object> getAttachments()
  {
    return this.attachments;
  }

  @JsonProperty("attachments")
  public void setAttachments(List<Object> attachments)
  {
    this.attachments = attachments;
  }

  @JsonProperty("avatar_url")
  public String getAvatar()
  {
    return this.avatar;
  }

  @JsonProperty("avatar_url")
  public void setAvatar(String avatar)
  {
    this.avatar = avatar;
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
}

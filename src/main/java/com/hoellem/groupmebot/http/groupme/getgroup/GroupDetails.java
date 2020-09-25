package com.hoellem.groupmebot.http.groupme.getgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GroupDetails
{
  @JsonProperty("group_id")
  private Integer groupId;
  @JsonProperty("name")
  private String name;
  @JsonProperty("members")
  private ArrayList<GroupMember> members;

  public Integer getGroupId()
  {
    return groupId;
  }

  public void setGroupId(Integer groupId)
  {
    this.groupId = groupId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public ArrayList<GroupMember> getMembers()
  {
    return members;
  }

  public void setMembers(ArrayList<GroupMember> members)
  {
    this.members = members;
  }
}

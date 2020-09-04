package com.hoellem.groupmebot.http.groupme.getgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GroupDetails
{
  @JsonProperty("members")
  private ArrayList<GroupMember> members;

  public ArrayList<GroupMember> getMembers()
  {
    return members;
  }

  public void setMembers(ArrayList<GroupMember> members)
  {
    this.members = members;
  }
}

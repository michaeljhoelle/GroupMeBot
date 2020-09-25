package com.hoellem.groupmebot.http.groupme.getgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class FindAllGroupsResponse
{
  @JsonProperty("response")
  private ArrayList<GroupDetails> groupDetailsList;

  public ArrayList<GroupDetails> getGroupDetailsList()
  {
    return groupDetailsList;
  }

  public void setGroupDetailsList(ArrayList<GroupDetails> groupDetailsList)
  {
    this.groupDetailsList = groupDetailsList;
  }
}

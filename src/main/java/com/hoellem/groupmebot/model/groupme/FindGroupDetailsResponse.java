package com.hoellem.groupmebot.model.groupme;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindGroupDetailsResponse
{
  @JsonProperty("response")
  private GroupDetails groupDetails;

  public GroupDetails getGroupDetails()
  {
    return groupDetails;
  }

  public void setGroupDetails(GroupDetails groupDetails)
  {
    this.groupDetails = groupDetails;
  }
}

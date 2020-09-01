package com.hoellem.groupmebot.http;

public class BaseHandlerResponse
{
  private final String status;

  public BaseHandlerResponse(String status)
  {
    this.status = status;
  }

  public String getStatus()
  {
    return status;
  }

}

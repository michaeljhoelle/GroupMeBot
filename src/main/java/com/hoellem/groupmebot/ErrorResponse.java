package com.hoellem.groupmebot;

public class ErrorResponse
{
  public final String message;

  public ErrorResponse()
  {
    this.message = "You shouldn't be here";
  }

  public String getMessage()
  {
    return this.message;
  }
}

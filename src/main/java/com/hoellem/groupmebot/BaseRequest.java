package com.hoellem.groupmebot;

public class BaseRequest
{
  public String text;

  public BaseRequest(String botId, String text)
  {
    this.text = text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public String getText()
  {
    return this.text;
  }

}

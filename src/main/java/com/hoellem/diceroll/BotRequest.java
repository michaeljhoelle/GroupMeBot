package com.hoellem.diceroll;

public class BotRequest
{
  public String bot_id;
  public String text;

  public BotRequest(String botId, String text)
  {
    this.bot_id = botId;
    this.text = text;
  }

  public void setBotId(String botId)
  {
    this.bot_id = botId;
  }

  public String getBotId()
  {
    return this.bot_id;
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

package com.hoellem.diceroll;

public class RollResponse
{
  private final long id;
  private final String content;

  public RollResponse(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}

package com.hoellem.groupmebot.http.Entity;

import javax.persistence.*;

@Entity
@Table(name = "todo_list")
public class Todo
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "todo_id")
  private int id;
  @Column(name = "groupme_user_id")
  private String userId;
  private String entry;

  public Todo()
  {

  }

  public Todo(String userId, String entry)
  {
    this.userId = userId;
    this.entry = entry;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setEntry(String entry)
  {
    this.entry = entry;
  }

  public String getEntry()
  {
    return entry;
  }
}

package com.hoellem.groupmebot.repository.todo;

import javax.persistence.*;

@Entity
@Table(name = "todo_list")
public class Todo
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "user_id")
  private Integer userId;
  private String entry;

  public Todo()
  {

  }

  public Todo(Integer userId, String entry)
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

  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }

  public Integer getUserId()
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

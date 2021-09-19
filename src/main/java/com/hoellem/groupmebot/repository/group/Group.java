package com.hoellem.groupmebot.repository.group;

import com.hoellem.groupmebot.repository.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "groupchat-entity-graph",
  attributeNodes = {
    @NamedAttributeNode("id"),
    @NamedAttributeNode("name"),
    @NamedAttributeNode("users")
  }
)
@Table(name = "groupchat")
public class Group
{
  @Id
  private Integer id;
  private String name;

  @ManyToMany(mappedBy = "groups")
  Set<User> users = new HashSet<>();

  public Group()
  {

  }

  public Group(int id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Set<User> getUsers()
  {
    return users;
  }

  public void setUsers(Set<User> users)
  {
    this.users = users;
  }

  public void addUser(User user)
  {
    users.add(user);
    user.getGroups().add(this);
  }

  public void removeUser(User user)
  {
    users.remove(user);
    user.getGroups().remove(this);
  }
}

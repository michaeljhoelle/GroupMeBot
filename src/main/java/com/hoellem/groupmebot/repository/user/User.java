package com.hoellem.groupmebot.repository.user;

import com.hoellem.groupmebot.repository.group.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "user-entity-graph",
    attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode("nickname"),
        @NamedAttributeNode("groups")
    }
)
@Table(name = "groupme_user")
public class User
{
  @Id
  private int id;
  private String name;
  private String nickname;
  @Column(name = "feet_count")
  private int feetCount;

  @ManyToMany
  @JoinTable(
      name = "user_group_map",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id"))
  Set<Group> groups = new HashSet<>();

  public User()
  {

  }

  public User(int id, String name, String nickname, Group group)
  {
    this.id = id;
    this.name = name;
    this.nickname = nickname;
    this.groups.add(group);
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
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

  public String getNickname()
  {
    return nickname;
  }

  public void setNickname(String nickname)
  {
    this.nickname = nickname;
  }

  public int getFeetCount()
  {
    return feetCount;
  }

  public void setFeetCount(int feetCount)
  {
    this.feetCount = feetCount;
  }

  public void incrementFeetCount()
  {
    feetCount++;
  }

  public Set<Group> getGroups()
  {
    return groups;
  }

  public void setGroups(Set<Group> groups)
  {
    this.groups = groups;
  }

  public void addGroup(Group group)
  {
    this.groups.add(group);
    group.getUsers().add(this);
  }

  public void removeGroup(Group group)
  {
    this.groups.remove(group);
    group.getUsers().remove(this);
  }
}

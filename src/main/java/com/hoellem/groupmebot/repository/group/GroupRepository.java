package com.hoellem.groupmebot.repository.group;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Integer>
{
  @EntityGraph(value = "groupchat-entity-graph")
  Optional<Group> getById(int id);
}

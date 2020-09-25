package com.hoellem.groupmebot.http.datasource.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer>
{
  @EntityGraph(value = "user-entity-graph")
  Optional<User> getById(int id);
}

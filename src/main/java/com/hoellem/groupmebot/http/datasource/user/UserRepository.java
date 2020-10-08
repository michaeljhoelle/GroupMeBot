package com.hoellem.groupmebot.http.datasource.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
  @EntityGraph(value = "user-entity-graph")
  Optional<User> getById(int id);

  @Query(value = "SELECT u FROM User u WHERE u.feetCount > 0 ORDER BY u.feetCount DESC")
  List<User> getTopFeetCounts();
}

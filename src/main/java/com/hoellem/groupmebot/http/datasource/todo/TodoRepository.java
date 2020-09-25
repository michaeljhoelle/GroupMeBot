package com.hoellem.groupmebot.http.datasource.todo;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer>
{
}

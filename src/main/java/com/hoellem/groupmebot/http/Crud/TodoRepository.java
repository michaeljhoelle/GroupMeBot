package com.hoellem.groupmebot.http.Crud;

import com.hoellem.groupmebot.http.Entity.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Integer>
{
}

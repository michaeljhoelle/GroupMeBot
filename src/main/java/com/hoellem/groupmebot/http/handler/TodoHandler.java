package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.http.datasource.todo.TodoRepository;
import com.hoellem.groupmebot.http.datasource.todo.Todo;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.http.groupme.GroupMeRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoHandler extends BaseHandler implements RequestHandler
{
  private TodoRepository todoRepository;

  @Autowired
  public void setTodoRepository(TodoRepository todo)
  {
    this.todoRepository = todo;
  }

  @Override
  public void handle(GroupMeRequest request)
  {
    Matcher matcher = parameterPattern.matcher(request.getText());
    if (matcher.find())
    {
      todoRepository.save(new Todo(request.getUserId(), matcher.group(1)));
      messenger.sendGroupMeMessage("Saved!");
    }
    else
    {
      messenger.sendGroupMeMessage(formattedTodoList());
    }
  }

  private String formattedTodoList()
  {
    StringBuilder responseText = new StringBuilder("Mike's Todo List:\n");
    Iterable<Todo> todoList = todoRepository.findAll();
    for (Todo todo: todoList)
    {
      responseText.append(todo.getEntry()).append("\n");
    }
    return responseText.toString();
  }
}

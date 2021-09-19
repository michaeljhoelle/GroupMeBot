package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.repository.todo.TodoRepository;
import com.hoellem.groupmebot.repository.todo.Todo;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

@Service
@RequiredArgsConstructor
public class TodoHandler implements RequestHandler
{
  private final GroupMeMessenger messenger;
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

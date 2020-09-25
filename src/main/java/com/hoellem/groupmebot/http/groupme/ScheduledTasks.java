package com.hoellem.groupmebot.http.groupme;

import com.hoellem.groupmebot.http.datasource.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks
{
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }

  @Scheduled(fixedRate = 86400000, initialDelay = 3600000)
  public void dailyUpdate()
  {
    userService.updateGroupMeUsers();
  }
}
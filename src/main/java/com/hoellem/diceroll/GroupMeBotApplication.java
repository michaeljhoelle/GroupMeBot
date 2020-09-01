package com.hoellem.diceroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupMeBotApplication
{
  private static final String url = "https://api.groupme.com/v3/bots/post";
  private static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);

  public static void main(String[] args)
  {
    SpringApplication.run(GroupMeBotApplication.class, args);
  }

}

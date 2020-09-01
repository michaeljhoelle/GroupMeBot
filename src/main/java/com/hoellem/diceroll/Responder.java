package com.hoellem.diceroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Responder
{
  private static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/error")
  public ErrorResponse error()
  {
    return new ErrorResponse();
  }

  @GetMapping("/")
  public RollResponse greeting(@RequestParam(value = "name", defaultValue = "World") String name)
  {
    logger.info("Responding to GroupMe");
    BotPoster poster = new BotPoster();
    poster.postJSON();
    return new RollResponse(counter.incrementAndGet(), String.format(template, name));
  }
}

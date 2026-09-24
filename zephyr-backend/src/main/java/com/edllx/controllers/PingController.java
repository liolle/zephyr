package com.edllx.controllers;

import java.time.LocalTime;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v{version}/ping")
public class PingController {

  public PingController() {

  }

  @GetMapping(path = { "", "/" }, version = "1")
  public String Ping() {
    return String.format("Pong : %s", LocalTime.now().toString());
  }

}

package com.edllx.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RootController {

  public RootController() {
  }

  @GetMapping("")
  public void Root() {

  }

}

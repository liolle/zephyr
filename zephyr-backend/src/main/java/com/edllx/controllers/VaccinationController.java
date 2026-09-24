package com.edllx.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v{version}/vaccinations")
public class VaccinationController {

  public VaccinationController() {

  }

  @PostMapping(path = { "/apply", "/apply/" }, version = "1")
  public String getStockListV1() {

    // TODO ENDPOINT /api/v1/vaccinations/administer
    return String.format("V1 : %s", "apply Vaccination");
  }

}

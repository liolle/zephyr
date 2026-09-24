package com.edllx.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edllx.dto.RestockVaccinRequest;

@RestController
@RequestMapping("/api/v{version}/stocks")
public class StockController {

  public StockController() {

  }

  @GetMapping(path = { "", "/" }, version = "1")
  public String getStockListV1() {

    // TODO ENDPOINT GET /api/v1/stocks
    return String.format("V1 : %s", "stocks");
  }

  @PostMapping(path = { "/add", "/add/" }, version = "1")
  public String RestockV1(@RequestBody RestockVaccinRequest body) {

    // TODO ENDPOINT POST /api/v1/stocks/add
    /*
     * {
     * vaccine_name : "",
     * lot_name : "",
     * quantity : "",
     * expiration_date : "",
     * }
     */

    return String.format("V1 : %s", body.toString());
  }

}

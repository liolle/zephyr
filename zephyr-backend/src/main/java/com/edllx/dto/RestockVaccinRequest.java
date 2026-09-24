package com.edllx.dto;

import java.time.LocalDate;

public class RestockVaccinRequest {

  private String vaccine_name;

  private String lot_name;

  private Integer quantity;

  private LocalDate expiration_date;

  public String getVaccine_name() {
    return vaccine_name;
  }

  public String getLot_name() {
    return lot_name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public LocalDate getExpiration_date() {
    return expiration_date;
  }

  @Override
  public String toString() {
    return String.format("Link: %s", "");
  }

}

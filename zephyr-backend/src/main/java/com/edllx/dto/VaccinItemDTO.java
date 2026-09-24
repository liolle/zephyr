package com.edllx.dto;

public class VaccinItemDTO {

  private String name;
  private String lot_name;
  private Integer quantity;

  public VaccinItemDTO(String name, String lot_name, Integer quantity) {
    this.name = name;
    this.lot_name = lot_name;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return String.format("%s : %s | x%d   ", name, lot_name, quantity);
  }

}

package com.edllx.dto;

public class VaccinationDTO {

  private String name;
  private String lot_name;

  public String getName() {
    return name;
  }

  public String getLot_name() {
    return lot_name;
  }

  public VaccinationDTO(String name, String lot_name) {
    this.name = name;
    this.lot_name = lot_name;
  }

  @Override
  public String toString() {
    return String.format("%s : %s    ", name, lot_name);
  }
}

package com.edllx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatePatientRequest {

  @NotBlank
  @Size(min = 11, max = 11)
  private String niss;

  private String guardian_niss;

  public String getNiss() {
    return niss;
  }

  public String getGuardian_niss() {
    return guardian_niss;
  }

  public void setNiss(String niss) {
    this.niss = niss;
  }

  public void setGuardian_niss(String guardian_niss) {
    this.guardian_niss = guardian_niss;
  }

  @Override
  public String toString() {
    return String.format("Link: %s : %s", niss, guardian_niss);
  }

}

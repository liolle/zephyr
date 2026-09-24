package com.edllx.dto;

import java.util.ArrayList;

public class PatientDTO {

  private String niss;
  private String guardian_niss;
  private String firstname;
  private String lastname;

  private VaccinationDTO[] vaccins = {};

  public PatientDTO(String niss, String guardian_niss, String firstname, String lastname) {
    this.niss = niss;
    this.guardian_niss = guardian_niss;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public PatientDTO(String niss, String guardian_niss, String firstname, String lastname, VaccinationDTO[] vaccins) {
    this.niss = niss;
    this.guardian_niss = guardian_niss;
    this.firstname = firstname;
    this.lastname = lastname;

    this.vaccins = vaccins;
  }

  public String getNiss() {
    return niss;
  }

  public String getGuardian_niss() {
    return guardian_niss;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public VaccinationDTO[] getVaccins() {
    return vaccins;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer
        .append(String.format("NISS: %s\nName: %s %s\nGuardian_NISS: %s\n", niss, firstname, lastname, guardian_niss));

    if (vaccins.length > 0) {

      buffer.append("\n   Vaccins:\n");
      for (VaccinationDTO vd : vaccins) {
        buffer.append(String.format("   \n", vd.toString()));
      }

    }

    return "";
  }

}

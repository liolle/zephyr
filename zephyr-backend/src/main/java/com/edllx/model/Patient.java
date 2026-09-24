package com.edllx.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

  @Id
  private String niss;

  @OneToOne
  @MapsId
  @JoinColumn(name = "niss", insertable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "guardian_niss")
  private User guardian;

  public void setUser(User user) {
    this.user = user;
  }

  public void setGuardian(User guardian) {
    this.guardian = guardian;
  }

  @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
  private List<VaccinationRecord> vaccinationRecords = new ArrayList<>();

  public Patient() {

  }

  public List<VaccinationRecord> getVaccinationRecords() {
    return vaccinationRecords;
  }

  public String getNiss() {
    return user.getNiss();
  }

  public String getFirstName() {
    return user != null ? user.getFirstName() : null;
  }

  public String getLastName() {
    return user != null ? user.getLastName() : null;
  }

  public String getGuardianNiss() {
    return guardian != null ? guardian.getNiss() : null;
  }
}

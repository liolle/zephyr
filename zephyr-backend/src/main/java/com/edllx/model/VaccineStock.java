package com.edllx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaccine_stocks", uniqueConstraints = @UniqueConstraint(name = "uq_vaccine_lot", columnNames = {
    "vaccine_name", "lot_name" }))
public class VaccineStock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "vaccine_name", length = 100, nullable = false)
  private String vaccineName;

  @Column(name = "lot_name", length = 50, nullable = false)
  private String lotNumber;

  @Min(0)
  @Column(name = "quantity_available", nullable = false)
  private Integer quantityAvailable;

  @Column(name = "expiration_date", nullable = false)
  private LocalDate expirationDate;

  @OneToMany(mappedBy = "vaccineStock", fetch = FetchType.LAZY)
  private List<VaccinationRecord> vaccinationRecords = new ArrayList<>();
}

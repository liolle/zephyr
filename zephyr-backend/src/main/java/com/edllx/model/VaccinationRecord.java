package com.edllx.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_records")
public class VaccinationRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "patient_niss", nullable = false)
  private Patient patient;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "administrator_niss", nullable = false)
  private User administrator;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "vaccine_stock_id", nullable = false)
  private VaccineStock vaccineStock;

  @Column(name = "administered_at", nullable = false)
  private LocalDateTime administeredAt;
}

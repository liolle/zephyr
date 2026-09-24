package com.edllx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edllx.model.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

  // Derived query method — Spring generates the SQL from the method name
  Optional<Patient> findByNiss(String niss);

  @Query("SELECT p FROM Patient p " +
      "LEFT JOIN FETCH p.vaccinationRecords " +
      "WHERE p.user.niss = :niss")
  Optional<Patient> findByNissWithVaccinations(@Param("niss") String niss);

}

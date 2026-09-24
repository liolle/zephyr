package com.edllx.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edllx.dto.CreatePatientRequest;
import com.edllx.dto.PatientDTO;
import com.edllx.dto.VaccinationDTO;
import com.edllx.model.Patient;
import com.edllx.model.User;
import com.edllx.model.VaccinationRecord;
import com.edllx.repository.PatientRepository;
import com.edllx.repository.UserRepository;

@Service
public class PatientService {

  private final PatientRepository patientRepository;
  private final UserRepository userRepository;

  public PatientService(PatientRepository patientRepository, UserRepository userRepository) {

    this.patientRepository = patientRepository;
    this.userRepository = userRepository;
  }

  public PatientDTO getPatientInfo(String niss) {

    var res = patientRepository.findByNiss(niss);
    if (res.isEmpty()) {

      return new PatientDTO(niss, "", "", "");
    }

    var patient = res.get();

    var dto = new PatientDTO(niss, patient.getGuardianNiss(), patient.getFirstName(), patient.getLastName());
    return dto;

  }

  public PatientDTO getPatientInfo(String niss, Boolean includeV) {

    return includeV ? getPatient(niss, includeV) : getPatientInfo(niss);
  }

  private PatientDTO getPatient(String niss, boolean includeVaccinations) {
    var res = patientRepository.findByNissWithVaccinations(niss);
    if (res.isEmpty()) {

      return new PatientDTO(niss, "", "", "");
    }

    var patient = res.get();

    List<VaccinationRecord> vaccinations = includeVaccinations
        ? patient.getVaccinationRecords()
        : List.of();

    VaccinationDTO[] vdto = {
    };

    var dto = new PatientDTO(
        patient.getNiss(),
        patient.getGuardianNiss(),
        patient.getFirstName(),
        patient.getLastName(), vdto);

    return dto;
  }

  public PatientDTO createPatient(CreatePatientRequest request) {
    User user = userRepository.findById(request.getNiss())
        .orElseThrow(() -> new IllegalArgumentException(
            "No user found with NISS " + request.getNiss()));

    Patient patient = new Patient();
    patient.setUser(user);

    if (request.getGuardian_niss() != null && !request.getGuardian_niss().isBlank()) {
      User guardian = userRepository.findById(request.getGuardian_niss())
          .orElseThrow(() -> new IllegalArgumentException(
              "No user found with NISS " + request.getGuardian_niss()));
      patient.setGuardian(guardian);
    }

    Patient saved = patientRepository.save(patient);
    return toDTO(saved);
  }

  private PatientDTO toDTO(Patient patient) {
    return new PatientDTO(
        patient.getNiss(),
        patient.getGuardianNiss(),
        patient.getFirstName(),
        patient.getLastName());
  }
}

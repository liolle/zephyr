package com.edllx.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edllx.dto.CreatePatientRequest;
import com.edllx.dto.PatientDTO;
import com.edllx.services.PatientService;

@RestController
@RequestMapping("/api/v{version}/patients")
public class PatientController {

  private PatientService patientService;

  public PatientController(PatientService patientService) {

    this.patientService = patientService;
  }

  @GetMapping(path = "{niss}", version = "1")
  public PatientDTO getPatientV1(@PathVariable String niss, @RequestParam(required = false) boolean includeV) {

    // TODO ENDPOINT GET /api/v1/patients/{niss}?include_v=true

    System.out.println(String.format("Hello - %b", includeV));
    return patientService.getPatientInfo(niss, includeV);
  }

  @PostMapping(path = "new", version = "1")
  public PatientDTO CreatePatientV1(@RequestBody CreatePatientRequest body) {

    // TODO ENDPOINT POST /api/v1/patients/new
    /*
     * {
     * niss : "",
     * guardian_niss : "",
     * }
     */

    return patientService.createPatient(body);
  }

}

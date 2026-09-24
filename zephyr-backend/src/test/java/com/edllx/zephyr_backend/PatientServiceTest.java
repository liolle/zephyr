package com.edllx.zephyr_backend;

import com.edllx.dto.CreatePatientRequest;
import com.edllx.dto.PatientDTO;
import com.edllx.model.Patient;
import com.edllx.model.User;
import com.edllx.repository.PatientRepository;
import com.edllx.repository.UserRepository;
import com.edllx.services.PatientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

  @Mock
  private PatientRepository patientRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private PatientService patientService;

  private User alice;
  private User bob;
  private Patient aliceAsPatient;

  @BeforeEach
  void setUp() {
    alice = new User();
    alice.setNiss("90010112345");
    alice.setFirstName("Alice");
    alice.setLastName("Dupont");
    alice.setBirthDate(LocalDate.of(1990, 1, 1));

    bob = new User();
    bob.setNiss("85050567890");
    bob.setFirstName("Bob");
    bob.setLastName("Martin");
    bob.setBirthDate(LocalDate.of(1985, 5, 5));

    aliceAsPatient = new Patient();
    aliceAsPatient.setUser(alice);
  }

  // ---------- getPatientInfo(niss) ----------

  @Test
  void getPatientInfo_returnsEmptyDto_whenPatientNotFound() {
    when(patientRepository.findByNiss("00000000000"))
        .thenReturn(Optional.empty());

    PatientDTO dto = patientService.getPatientInfo("00000000000");

    assertThat(dto.getNiss()).isEqualTo("00000000000");
    assertThat(dto.getFirstname()).isEmpty();
    assertThat(dto.getLastname()).isEmpty();
  }

  @Test
  void getPatientInfo_returnsPatientData_whenFound() {
    when(patientRepository.findByNiss(alice.getNiss()))
        .thenReturn(Optional.of(aliceAsPatient));

    PatientDTO dto = patientService.getPatientInfo(alice.getNiss());

    assertThat(dto.getNiss()).isEqualTo("90010112345");
    assertThat(dto.getFirstname()).isEqualTo("Alice");
    assertThat(dto.getLastname()).isEqualTo("Dupont");
    assertThat(dto.getGuardian_niss()).isNull();
  }

  // ---------- getPatientInfo(niss, includeV) ----------

  @Test
  void getPatientInfo_withIncludeFalse_delegatesToPlainVersion() {
    when(patientRepository.findByNiss(alice.getNiss()))
        .thenReturn(Optional.of(aliceAsPatient));

    PatientDTO dto = patientService.getPatientInfo(alice.getNiss(), false);

    assertThat(dto.getFirstname()).isEqualTo("Alice");
    verify(patientRepository).findByNiss(alice.getNiss());
    verify(patientRepository, never()).findByNissWithVaccinations(any());
  }

  @Test
  void getPatientInfo_withIncludeTrue_usesVaccinationQuery() {
    when(patientRepository.findByNissWithVaccinations(alice.getNiss()))
        .thenReturn(Optional.of(aliceAsPatient));

    PatientDTO dto = patientService.getPatientInfo(alice.getNiss(), true);

    assertThat(dto.getFirstname()).isEqualTo("Alice");
    verify(patientRepository).findByNissWithVaccinations(alice.getNiss());
    verify(patientRepository, never()).findByNiss(any());
  }

  // ---------- createPatient ----------

  @Test
  void createPatient_savesPatient_whenUserExists() {
    CreatePatientRequest request = new CreatePatientRequest();
    request.setNiss(alice.getNiss());

    when(userRepository.findById(alice.getNiss()))
        .thenReturn(Optional.of(alice));
    when(patientRepository.save(any(Patient.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    PatientDTO dto = patientService.createPatient(request);

    assertThat(dto.getNiss()).isEqualTo(alice.getNiss());
    assertThat(dto.getFirstname()).isEqualTo(alice.getFirstName());
    assertThat(dto.getGuardian_niss()).isNull();

    verify(patientRepository).save(any(Patient.class));
  }

  @Test
  void createPatient_setsGuardian_whenGuardianNissProvided() {
    CreatePatientRequest request = new CreatePatientRequest();
    request.setNiss(alice.getNiss());
    request.setGuardian_niss(bob.getNiss());

    when(userRepository.findById(alice.getNiss()))
        .thenReturn(Optional.of(alice));
    when(userRepository.findById(bob.getNiss()))
        .thenReturn(Optional.of(bob));
    when(patientRepository.save(any(Patient.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    PatientDTO dto = patientService.createPatient(request);

    assertThat(dto.getGuardian_niss()).isEqualTo("85050567890");
    verify(userRepository, times(2)).findById(anyString());
  }

  @Test
  void createPatient_throws_whenUserDoesNotExist() {
    CreatePatientRequest request = new CreatePatientRequest();
    request.setNiss("00000000000");

    when(userRepository.findById("00000000000"))
        .thenReturn(Optional.empty());

    assertThatThrownBy(() -> patientService.createPatient(request))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("No user found");

    verify(patientRepository, never()).save(any());
  }

  @Test
  void createPatient_throws_whenGuardianDoesNotExist() {
    CreatePatientRequest request = new CreatePatientRequest();
    request.setNiss(alice.getNiss());
    request.setGuardian_niss("00000000000");

    when(userRepository.findById(alice.getNiss()))
        .thenReturn(Optional.of(alice));
    when(userRepository.findById("00000000000"))
        .thenReturn(Optional.empty());

    assertThatThrownBy(() -> patientService.createPatient(request))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("No user found");

    verify(patientRepository, never()).save(any());
  }

  @Test
  void createPatient_ignoresBlankGuardian() {
    CreatePatientRequest request = new CreatePatientRequest();
    request.setNiss(alice.getNiss());
    request.setGuardian_niss("   "); // blank, should be ignored

    when(userRepository.findById(alice.getNiss()))
        .thenReturn(Optional.of(alice));
    when(patientRepository.save(any(Patient.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    PatientDTO dto = patientService.createPatient(request);

    assertThat(dto.getGuardian_niss()).isNull();
    // Only one lookup: the user. The guardian lookup should be skipped.
    verify(userRepository, times(1)).findById(anyString());
  }
}

package com.hpms.services;

import com.hpms.domain.Patient;
import com.hpms.repositories.PatientRepository;
import com.hpms.services.exceptions.BusinessRuleException;
import com.hpms.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(String firstName, String lastName, LocalDate dateOfBirth, String diagnosis) {
        validateRequired(firstName, "firstName");
        validateRequired(lastName, "lastName");
        validateRequired(diagnosis, "diagnosis");
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new BusinessRuleException("dateOfBirth must be a valid past date.");
        }

        Patient patient = new Patient(firstName, lastName, dateOfBirth, diagnosis);
        patientRepository.save(patient);
        return patient;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(UUID id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
    }

    public Patient updateDiagnosis(UUID id, String diagnosis) {
        validateRequired(diagnosis, "diagnosis");
        Patient patient = getPatientById(id);
        patient.setDiagnosis(diagnosis);
        patientRepository.save(patient);
        return patient;
    }

    public Patient admit(UUID id) {
        Patient patient = getPatientById(id);
        if ("ADMITTED".equals(patient.getStatus())) {
            throw new BusinessRuleException("Patient is already admitted.");
        }
        patient.register();
        patientRepository.save(patient);
        return patient;
    }

    public Patient discharge(UUID id) {
        Patient patient = getPatientById(id);
        if (!"ADMITTED".equals(patient.getStatus())) {
            throw new BusinessRuleException("Only admitted patients can be discharged.");
        }
        patient.discharge();
        patientRepository.save(patient);
        return patient;
    }

    public void deletePatient(UUID id) {
        getPatientById(id);
        patientRepository.delete(id);
    }

    private void validateRequired(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new BusinessRuleException(fieldName + " is required.");
        }
    }
}
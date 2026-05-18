package com.hpms.api;

import com.hpms.api.dto.DiagnosisUpdateRequest;
import com.hpms.api.dto.PatientCreateRequest;
import com.hpms.domain.Patient;
import com.hpms.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Fetch all patients")
    public List<Patient> getAll() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch patient by id")
    public Patient getById(@PathVariable("id") UUID id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new patient")
    public ResponseEntity<Patient> create(@RequestBody PatientCreateRequest request) {
        Patient created = patientService.createPatient(
                request.firstName(),
                request.lastName(),
                request.dateOfBirth(),
                request.diagnosis()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/diagnosis")
    @Operation(summary = "Update patient diagnosis")
    public Patient updateDiagnosis(@PathVariable("id") UUID id, @RequestBody DiagnosisUpdateRequest request) {
        return patientService.updateDiagnosis(id, request.diagnosis());
    }

    @PostMapping("/{id}/admit")
    @Operation(summary = "Admit patient")
    public Patient admit(@PathVariable("id") UUID id) {
        return patientService.admit(id);
    }

    @PostMapping("/{id}/discharge")
    @Operation(summary = "Discharge patient")
    public Patient discharge(@PathVariable("id") UUID id) {
        return patientService.discharge(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
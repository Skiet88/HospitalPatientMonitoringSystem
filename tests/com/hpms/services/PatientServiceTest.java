package com.hpms.services;

import com.hpms.domain.Patient;
import com.hpms.repositories.inmemory.InMemoryPatientRepository;
import com.hpms.services.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientServiceTest {
    @Test
    void dischargeRequiresAdmittedStatus() {
        PatientService service = new PatientService(new InMemoryPatientRepository());
        Patient patient = service.createPatient("Ana", "Doe", LocalDate.of(1995, 3, 10), "Fever");

        assertThrows(BusinessRuleException.class, () -> service.discharge(patient.getPatientId()));

        service.admit(patient.getPatientId());
        Patient discharged = service.discharge(patient.getPatientId());
        assertEquals("DISCHARGED", discharged.getStatus());
    }
}
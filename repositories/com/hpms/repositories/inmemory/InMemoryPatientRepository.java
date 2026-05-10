package com.hpms.repositories.inmemory;

import com.hpms.domain.Patient;
import com.hpms.repositories.PatientRepository;

public class InMemoryPatientRepository extends AbstractInMemoryRepository<Patient, java.util.UUID> implements PatientRepository {
    public InMemoryPatientRepository() {
        super(Patient::getPatientId);
    }
}
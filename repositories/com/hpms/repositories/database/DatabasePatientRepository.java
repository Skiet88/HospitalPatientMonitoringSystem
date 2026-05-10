package com.hpms.repositories.database;

import com.hpms.domain.Patient;
import com.hpms.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DatabasePatientRepository implements PatientRepository {
    @Override
    public void save(Patient entity) {
        throw new UnsupportedOperationException("DatabasePatientRepository is a future stub and not implemented yet.");
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        throw new UnsupportedOperationException("DatabasePatientRepository is a future stub and not implemented yet.");
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("DatabasePatientRepository is a future stub and not implemented yet.");
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("DatabasePatientRepository is a future stub and not implemented yet.");
    }
}
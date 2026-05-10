package com.hpms.factories;

import com.hpms.repositories.AlertRepository;
import com.hpms.repositories.PatientRepository;
import com.hpms.repositories.UserRepository;
import com.hpms.repositories.database.DatabasePatientRepository;
import com.hpms.repositories.inmemory.InMemoryAlertRepository;
import com.hpms.repositories.inmemory.InMemoryPatientRepository;
import com.hpms.repositories.inmemory.InMemoryUserRepository;

public final class RepositoryFactory {
    public enum StorageType {
        MEMORY,
        DATABASE
    }

    private RepositoryFactory() {
    }

    public static PatientRepository getPatientRepository(StorageType storageType) {
        return switch (storageType) {
            case MEMORY -> new InMemoryPatientRepository();
            case DATABASE -> new DatabasePatientRepository();
        };
    }

    public static UserRepository getUserRepository(StorageType storageType) {
        return switch (storageType) {
            case MEMORY -> new InMemoryUserRepository();
            case DATABASE -> throw new UnsupportedOperationException("DatabaseUserRepository is not implemented yet.");
        };
    }

    public static AlertRepository getAlertRepository(StorageType storageType) {
        return switch (storageType) {
            case MEMORY -> new InMemoryAlertRepository();
            case DATABASE -> throw new UnsupportedOperationException("DatabaseAlertRepository is not implemented yet.");
        };
    }
}
package com.hpms.repositories;

import com.hpms.domain.Alert;
import com.hpms.domain.Patient;
import com.hpms.domain.User;
import com.hpms.factories.RepositoryFactory;
import com.hpms.repositories.database.DatabasePatientRepository;
import com.hpms.repositories.inmemory.InMemoryAlertRepository;
import com.hpms.repositories.inmemory.InMemoryPatientRepository;
import com.hpms.repositories.inmemory.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositoryLayerTest {

    @Test
    void patientRepositorySupportsCrudOperations() {
        InMemoryPatientRepository repository = new InMemoryPatientRepository();
        Patient patient = new Patient("Lena", "Moyo", LocalDate.of(1999, 3, 10), "Flu");

        repository.save(patient);
        Optional<Patient> found = repository.findById(patient.getPatientId());

        assertTrue(found.isPresent());
        assertEquals("Flu", found.get().getDiagnosis());

        patient.setDiagnosis("Recovered");
        repository.save(patient);
        assertEquals("Recovered", repository.findById(patient.getPatientId()).orElseThrow().getDiagnosis());

        assertEquals(1, repository.findAll().size());
        repository.delete(patient.getPatientId());
        assertTrue(repository.findById(patient.getPatientId()).isEmpty());
    }

    @Test
    void userRepositorySupportsCrudOperations() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        User user = new User("Theo", "Ndlovu", "theo@hpms.org", "password123", "NURSE", UUID.randomUUID());

        repository.save(user);
        assertTrue(repository.findById(user.getUserId()).isPresent());
        assertEquals(1, repository.findAll().size());

        repository.delete(user.getUserId());
        assertTrue(repository.findById(user.getUserId()).isEmpty());
    }

    @Test
    void alertRepositorySupportsCrudOperations() {
        InMemoryAlertRepository repository = new InMemoryAlertRepository();
        Alert alert = new Alert(UUID.randomUUID(), UUID.randomUUID(), "heartRate", 130.0, "WARNING");

        repository.save(alert);
        assertTrue(repository.findById(alert.getAlertId()).isPresent());

        alert.trigger();
        repository.save(alert);
        assertEquals("OPEN", repository.findById(alert.getAlertId()).orElseThrow().getStatus());
    }

    @Test
    void repositoryFactoryReturnsExpectedImplementations() {
        assertInstanceOf(
                InMemoryPatientRepository.class,
                RepositoryFactory.getPatientRepository(RepositoryFactory.StorageType.MEMORY)
        );
        assertInstanceOf(
                DatabasePatientRepository.class,
                RepositoryFactory.getPatientRepository(RepositoryFactory.StorageType.DATABASE)
        );
        assertThrows(
                UnsupportedOperationException.class,
                () -> RepositoryFactory.getUserRepository(RepositoryFactory.StorageType.DATABASE)
        );
    }
}
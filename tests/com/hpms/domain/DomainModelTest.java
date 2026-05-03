package com.hpms.domain;

import com.hpms.service.AlertEngine;
import com.hpms.service.NotificationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainModelTest {

    @Test
    void patientLifecycleAndWardAssignmentWorks() {
        Ward ward = new Ward("ICU", "3", 2);
        Patient patient = new Patient("Ava", "Smith", LocalDate.of(1990, 1, 1), "Asthma");

        patient.register();
        ward.addPatient(patient);

        assertEquals("ADMITTED", patient.getStatus());
        assertEquals(ward.getWardId(), patient.getWardId());
        assertEquals(1, ward.getCurrentOccupancy());
    }

    @Test
    void wardCapacityIsEnforced() {
        Ward ward = new Ward("ER", "1", 1);
        Patient first = new Patient("John", "Doe", LocalDate.of(1985, 2, 1), "Fever");
        Patient second = new Patient("Lia", "Cole", LocalDate.of(1995, 3, 1), "Flu");

        ward.addPatient(first);
        assertThrows(IllegalStateException.class, () -> ward.addPatient(second));
    }

    @Test
    void alertEngineGeneratesAlertOnThresholdBreach() {
        UUID patientId = UUID.randomUUID();
        VitalReading reading = new VitalReading(patientId, 120, 130, 85, 98, 36.8, 16, "device-1");
        AlertThreshold threshold = new AlertThreshold(null, "heartRate", 60, 100, "WARNING", false, UUID.randomUUID());
        NotificationService notificationService = new NotificationService();
        AlertEngine alertEngine = new AlertEngine(notificationService);

        Alert alert = alertEngine.evaluateReading(reading, List.of(threshold));

        assertNotNull(alert);
        assertEquals("OPEN", alert.getStatus());
        assertTrue(notificationService.getDeliveryLog().size() > 0);
    }
}
package com.hpms.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Ward {
    private final UUID wardId;
    private final String name;
    private final String floor;
    private final int capacity;
    private int currentOccupancy;
    private String status;
    private final List<Patient> patients;
    private final List<User> users;

    public Ward(String name, String floor, int capacity) {
        this.wardId = UUID.randomUUID();
        this.name = name;
        this.floor = floor;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.status = "ACTIVE";
        this.patients = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        if (isAtCapacity()) {
            throw new IllegalStateException("Ward is at maximum capacity.");
        }
        if (patients.stream().anyMatch(p -> p.getPatientId().equals(patient.getPatientId()))) {
            throw new IllegalArgumentException("Patient already assigned to this ward.");
        }
        patient.assignToWard(wardId);
        patients.add(patient);
        currentOccupancy++;
    }

    public void removePatient(UUID patientId) {
        boolean removed = patients.removeIf(p -> p.getPatientId().equals(patientId));
        if (removed) {
            currentOccupancy--;
        }
    }

    public List<Alert> getActiveAlerts() {
        return patients.stream()
                .flatMap(patient -> patient.getActiveAlerts().stream())
                .toList();
    }

    public WardSummary getSummary() {
        return new WardSummary(name, floor, capacity, currentOccupancy, getActiveAlerts().size());
    }

    public boolean isAtCapacity() {
        return currentOccupancy >= capacity;
    }

    public void assignUser(User user) {
        user.setWardId(wardId);
        users.add(user);
    }

    public UUID getWardId() {
        return wardId;
    }

    public String getName() {
        return name;
    }

    public String getFloor() {
        return floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Patient> getPatients() {
        return Collections.unmodifiableList(patients);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
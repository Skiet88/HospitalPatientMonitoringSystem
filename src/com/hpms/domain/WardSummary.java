package com.hpms.domain;

public record WardSummary(String name, String floor, int capacity, int currentOccupancy, int activeAlerts) {
}
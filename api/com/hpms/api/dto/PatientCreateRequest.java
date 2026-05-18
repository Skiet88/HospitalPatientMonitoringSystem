package com.hpms.api.dto;

import java.time.LocalDate;

public record PatientCreateRequest(String firstName, String lastName, LocalDate dateOfBirth, String diagnosis) {
}
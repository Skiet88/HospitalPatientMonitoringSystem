package com.hpms.api.dto;

import java.util.UUID;

public record AlertCreateRequest(UUID patientId,
                                 UUID vitalReadingId,
                                 String vitalType,
                                 double triggeredValue,
                                 String severity) {
}
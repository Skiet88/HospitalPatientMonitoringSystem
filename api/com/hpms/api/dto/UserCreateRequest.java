package com.hpms.api.dto;

import java.util.UUID;

public record UserCreateRequest(String firstName,
                                String lastName,
                                String email,
                                String password,
                                String role,
                                UUID wardId) {
}
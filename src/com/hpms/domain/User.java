package com.hpms.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private final UUID userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private String passwordHash;
    private final String role;
    private UUID wardId;
    private boolean isActive;
    private LocalDateTime lastLoginAt;
    private int failedLoginAttempts;
    private LocalDateTime firstFailedAttemptAt;

    public User(String firstName, String lastName, String email, String passwordHash, String role, UUID wardId) {
        this.userId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.wardId = wardId;
        this.isActive = true;
    }

    public String login(String email, String password) {
        if (!isActive) {
            throw new IllegalStateException("User account is inactive.");
        }
        if (!this.email.equals(email) || !this.passwordHash.equals(password)) {
            registerFailedAttempt();
            throw new IllegalArgumentException("Invalid credentials.");
        }
        failedLoginAttempts = 0;
        firstFailedAttemptAt = null;
        lastLoginAt = LocalDateTime.now();
        return "token-" + UUID.randomUUID();
    }

    private void registerFailedAttempt() {
        LocalDateTime now = LocalDateTime.now();
        if (firstFailedAttemptAt == null || Duration.between(firstFailedAttemptAt, now).toMinutes() >= 1) {
            firstFailedAttemptAt = now;
            failedLoginAttempts = 0;
        }
        failedLoginAttempts++;
        if (failedLoginAttempts >= 5) {
            isActive = false;
            throw new IllegalStateException("User account locked after repeated failed attempts.");
        }
    }

    public void logout() {
        this.lastLoginAt = LocalDateTime.now();
    }

    public void resetPassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters.");
        }
        this.passwordHash = newPassword;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public UUID getWardId() {
        return wardId;
    }

    public void setWardId(UUID wardId) {
        this.wardId = wardId;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }
}
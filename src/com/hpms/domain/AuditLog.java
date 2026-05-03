package com.hpms.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditLog {
    private final UUID logId;
    private final UUID userId;
    private final String action;
    private final String targetEntity;
    private final UUID targetId;
    private final LocalDateTime timestamp;
    private final String ipAddress;

    private AuditLog(UUID userId, String action, String targetEntity, UUID targetId, String ipAddress) {
        this.logId = UUID.randomUUID();
        this.userId = userId;
        this.action = action;
        this.targetEntity = targetEntity;
        this.targetId = targetId;
        this.timestamp = LocalDateTime.now();
        this.ipAddress = ipAddress;
    }

    public static AuditLog create(UUID userId, String action, String targetEntity, UUID targetId, String ipAddress) {
        return new AuditLog(userId, action, targetEntity, targetId, ipAddress);
    }

    public String export(String format) {
        if ("CSV".equalsIgnoreCase(format)) {
            return logId + "," + userId + "," + action + "," + targetEntity + "," + targetId + "," + timestamp + "," + ipAddress;
        }
        return "AuditLog{" +
                "logId=" + logId +
                ", userId=" + userId +
                ", action='" + action + '\'' +
                ", targetEntity='" + targetEntity + '\'' +
                ", targetId=" + targetId +
                ", timestamp=" + timestamp +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }

    public UUID getLogId() {
        return logId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public UUID getTargetId() {
        return targetId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
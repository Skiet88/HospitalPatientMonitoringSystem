package com.hpms.service;

import com.hpms.domain.Alert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NotificationService {
    private final List<String> deliveryLog = new ArrayList<>();

    public void sendInAppNotification(UUID userId, Alert alert) {
        logDelivery(alert.getAlertId(), "IN_APP for user " + userId);
    }

    public void sendEmailNotification(String email, Alert alert) {
        logDelivery(alert.getAlertId(), "EMAIL to " + email);
    }

    public void logDelivery(UUID alertId, String channel) {
        deliveryLog.add("alertId=" + alertId + ",channel=" + channel);
    }

    public List<String> getDeliveryLog() {
        return Collections.unmodifiableList(deliveryLog);
    }
}
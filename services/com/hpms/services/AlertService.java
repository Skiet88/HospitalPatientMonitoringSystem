package com.hpms.services;

import com.hpms.domain.Alert;
import com.hpms.repositories.AlertRepository;
import com.hpms.services.exceptions.BusinessRuleException;
import com.hpms.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertService {
    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert createAlert(UUID patientId,
                             UUID vitalReadingId,
                             String vitalType,
                             double triggeredValue,
                             String severity) {
        validateRequired(vitalType, "vitalType");
        validateRequired(severity, "severity");
        if (triggeredValue <= 0) {
            throw new BusinessRuleException("triggeredValue must be greater than 0.");
        }

        Alert alert = new Alert(patientId, vitalReadingId, vitalType, triggeredValue, severity);
        alertRepository.save(alert);
        return alert;
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlertById(UUID id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found: " + id));
    }

    public Alert triggerAlert(UUID id) {
        Alert alert = getAlertById(id);
        alert.trigger();
        alertRepository.save(alert);
        return alert;
    }

    public Alert acknowledgeAlert(UUID id, UUID userId) {
        Alert alert = getAlertById(id);
        alert.acknowledge(userId);
        alertRepository.save(alert);
        return alert;
    }

    public Alert closeAlert(UUID id) {
        Alert alert = getAlertById(id);
        alert.close();
        alertRepository.save(alert);
        return alert;
    }

    public void deleteAlert(UUID id) {
        getAlertById(id);
        alertRepository.delete(id);
    }

    private void validateRequired(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new BusinessRuleException(fieldName + " is required.");
        }
    }
}
package com.hpms.api;

import com.hpms.api.dto.AlertAcknowledgeRequest;
import com.hpms.api.dto.AlertCreateRequest;
import com.hpms.domain.Alert;
import com.hpms.services.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alerts")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    @Operation(summary = "Fetch all alerts")
    public List<Alert> getAll() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch alert by id")
    public Alert getById(@PathVariable("id") UUID id) {
        return alertService.getAlertById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new alert")
    public ResponseEntity<Alert> create(@RequestBody AlertCreateRequest request) {
        Alert created = alertService.createAlert(
                request.patientId(),
                request.vitalReadingId(),
                request.vitalType(),
                request.triggeredValue(),
                request.severity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/trigger")
    @Operation(summary = "Trigger alert workflow")
    public Alert trigger(@PathVariable("id") UUID id) {
        return alertService.triggerAlert(id);
    }

    @PostMapping("/{id}/acknowledge")
    @Operation(summary = "Acknowledge alert")
    public Alert acknowledge(@PathVariable("id") UUID id, @RequestBody AlertAcknowledgeRequest request) {
        return alertService.acknowledgeAlert(id, request.userId());
    }

    @PostMapping("/{id}/close")
    @Operation(summary = "Close alert")
    public Alert close(@PathVariable("id") UUID id) {
        return alertService.closeAlert(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete alert")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}
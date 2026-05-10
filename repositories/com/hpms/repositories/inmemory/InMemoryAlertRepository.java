package com.hpms.repositories.inmemory;

import com.hpms.domain.Alert;
import com.hpms.repositories.AlertRepository;

public class InMemoryAlertRepository extends AbstractInMemoryRepository<Alert, java.util.UUID> implements AlertRepository {
    public InMemoryAlertRepository() {
        super(Alert::getAlertId);
    }
}
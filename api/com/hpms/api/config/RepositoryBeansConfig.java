package com.hpms.api.config;

import com.hpms.repositories.AlertRepository;
import com.hpms.repositories.PatientRepository;
import com.hpms.repositories.UserRepository;
import com.hpms.repositories.inmemory.InMemoryAlertRepository;
import com.hpms.repositories.inmemory.InMemoryPatientRepository;
import com.hpms.repositories.inmemory.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryBeansConfig {
    @Bean
    public PatientRepository patientRepository() {
        return new InMemoryPatientRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public AlertRepository alertRepository() {
        return new InMemoryAlertRepository();
    }
}
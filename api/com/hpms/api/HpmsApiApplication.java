package com.hpms.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hpms")
public class HpmsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HpmsApiApplication.class, args);
    }
}
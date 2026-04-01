package com.foodcourt.traceability_microservice_foodcourt.infrastructure.exception;

public class SecurityConfigurationException extends RuntimeException {
    public SecurityConfigurationException() {
        super("Error configuring the security filter chain");
    }
}

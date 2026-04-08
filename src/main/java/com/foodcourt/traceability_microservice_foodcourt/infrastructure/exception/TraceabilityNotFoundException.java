package com.foodcourt.traceability_microservice_foodcourt.infrastructure.exception;

public class TraceabilityNotFoundException extends RuntimeException {
    public TraceabilityNotFoundException() {
        super("No data founded");
    }
}

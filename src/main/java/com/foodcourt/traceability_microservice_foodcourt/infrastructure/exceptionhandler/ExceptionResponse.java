package com.foodcourt.traceability_microservice_foodcourt.infrastructure.exceptionhandler;

public enum ExceptionResponse {

    ACCESS_DENIED("You do not have permission to access this resource"),
    VALIDATION_ERROR("Validation error"),
    SECURITY_CONFIGURATION_ERROR("Error configuring security"),
    DATA_NOT_FOUND("Data not found"),
    ILLEGAL_ARGUMENT_ERROR("Error in one argument");


    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
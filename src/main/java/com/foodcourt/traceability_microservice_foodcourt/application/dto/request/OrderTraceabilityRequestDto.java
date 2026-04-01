package com.foodcourt.traceability_microservice_foodcourt.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTraceabilityRequestDto {

    @NotNull(message="The order id cannot be empty")
    @Positive(message="The order id cannot be empty be negative")
    private Long orderId;

    @NotNull(message="The client id cannot be empty")
    @Positive(message="The client id cannot be empty be negative")
    private Long clientId;

    @NotBlank(message="The client email cannot be empty")
    @Email(message="It must be in the format @example.com")
    private String clientEmail;

    private String previousStatus;

    @NotBlank(message="The newStatus cannot be empty")
    private String newStatus;

    @Positive(message="The employee id cannot be empty be negative")
    private Long employeeId;

    @Email(message="It must be in the format @example.com")
    private String employeeEmail;

}

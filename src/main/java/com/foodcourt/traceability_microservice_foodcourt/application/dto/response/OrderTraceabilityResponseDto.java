package com.foodcourt.traceability_microservice_foodcourt.application.dto.response;

import java.time.LocalDateTime;

public class OrderTraceabilityResponseDto {

    private Long orderId;
    private String previousStatus;
    private String newStatus;
    private LocalDateTime date;
}

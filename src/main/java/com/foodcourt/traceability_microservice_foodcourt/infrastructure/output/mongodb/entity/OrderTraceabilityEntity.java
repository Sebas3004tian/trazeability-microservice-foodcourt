package com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "order_traceability")
public class OrderTraceabilityEntity {

    @Id
    private String id;

    private Long orderId;
    private String clientId;
    private String clientEmail;

    private LocalDateTime date;

    private String previousStatus;
    private String newStatus;

    private Long employeeId;
    private String employeeEmail;
}
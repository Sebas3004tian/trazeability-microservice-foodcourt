package com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "order_traceability")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderTraceabilityEntity {

    @Id
    private String id;

    @Field("order_id")
    private Long orderId;

    @Field("client_id")
    private Long clientId;

    @Field("client_email")
    private String clientEmail;

    private LocalDateTime date;

    @Field("previous_status")
    private String previousStatus;

    @Field("new_status")
    private String newStatus;

    @Field("employee_id")
    private Long employeeId;

    @Field("employee_email")
    private String employeeEmail;
}
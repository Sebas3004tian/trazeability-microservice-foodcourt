package com.foodcourt.traceability_microservice_foodcourt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEfficiency {

    private Long employeeId;
    private int totalOrders;
    private Duration averageDuration;
}

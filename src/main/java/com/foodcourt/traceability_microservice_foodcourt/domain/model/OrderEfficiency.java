package com.foodcourt.traceability_microservice_foodcourt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEfficiency {
    private Long orderId;
    private Long employeeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Duration duration;

    public OrderEfficiency(Long orderId,Long employeeId, LocalDateTime startDate, LocalDateTime endDate) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        if (startDate != null && endDate != null) {
            this.duration = Duration.between(startDate, endDate);
        }
    }

    public Long getDurationInMinutes() {
        return duration != null ? duration.toMinutes() : null;
    }
}

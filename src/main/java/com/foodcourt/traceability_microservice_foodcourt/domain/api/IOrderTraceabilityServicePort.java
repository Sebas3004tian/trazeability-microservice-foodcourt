package com.foodcourt.traceability_microservice_foodcourt.domain.api;

import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;

import java.util.List;

public interface IOrderTraceabilityServicePort {

    void saveTraceability(OrderTraceability orderTraceability);

    List<OrderTraceability> getTraceability(Long orderId, Long clientId);
}
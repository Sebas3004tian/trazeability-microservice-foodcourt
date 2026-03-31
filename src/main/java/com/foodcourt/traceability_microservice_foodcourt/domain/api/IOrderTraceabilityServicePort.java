package com.foodcourt.traceability_microservice_foodcourt.domain.api;

import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;

import java.util.List;

public interface IOrderTraceabilityServicePort {

    void saveTraceability(OrderTraceability traceability);

    List<OrderTraceability> getTraceability(Long orderId, String clientId);
}
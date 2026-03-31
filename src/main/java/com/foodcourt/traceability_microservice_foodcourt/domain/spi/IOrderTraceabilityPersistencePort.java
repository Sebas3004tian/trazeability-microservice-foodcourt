package com.foodcourt.traceability_microservice_foodcourt.domain.spi;

import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;

import java.util.List;

public interface IOrderTraceabilityPersistencePort {

    void save(OrderTraceability traceability);

    List<OrderTraceability> findByOrderIdAndClientId(Long orderId, String clientId);
}

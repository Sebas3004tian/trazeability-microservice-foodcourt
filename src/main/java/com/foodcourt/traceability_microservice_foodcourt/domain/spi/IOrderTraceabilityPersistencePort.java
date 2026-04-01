package com.foodcourt.traceability_microservice_foodcourt.domain.spi;

import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;

import java.util.List;

public interface IOrderTraceabilityPersistencePort {

    OrderTraceability saveTraceability(OrderTraceability orderTraceability);

    List<OrderTraceability> findByOrderIdAndClientId(Long orderId, Long clientId);
}

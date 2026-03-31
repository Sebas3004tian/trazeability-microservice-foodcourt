package com.foodcourt.traceability_microservice_foodcourt.domain.usecase;

import com.foodcourt.traceability_microservice_foodcourt.domain.api.IOrderTraceabilityServicePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;

import java.util.List;

public class OrderTraceabilityUseCase implements IOrderTraceabilityServicePort {

    private final IOrderTraceabilityPersistencePort persistencePort;

    public OrderTraceabilityUseCase(IOrderTraceabilityPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void saveTraceability(OrderTraceability traceability) {
        persistencePort.save(traceability);
    }

    @Override
    public List<OrderTraceability> getTraceability(Long orderId, String clientId) {
        return persistencePort.findByOrderIdAndClientId(orderId, clientId);
    }
}

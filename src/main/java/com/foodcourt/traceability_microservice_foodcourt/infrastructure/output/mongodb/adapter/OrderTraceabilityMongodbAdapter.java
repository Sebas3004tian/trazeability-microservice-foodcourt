package com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.adapter;

import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.exception.TraceabilityNotFoundException;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.entity.OrderTraceabilityEntity;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.mapper.IOrderTraceabilityEntityMapper;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.repository.IOrderTraceabilityRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class OrderTraceabilityMongodbAdapter implements IOrderTraceabilityPersistencePort {

    private final IOrderTraceabilityRepository orderTraceabilityRepository;
    private final IOrderTraceabilityEntityMapper orderTraceabilityEntityMapper;

    @Override
    public OrderTraceability saveTraceability(OrderTraceability orderTraceability) {
        OrderTraceabilityEntity orderTraceabilityEntity = orderTraceabilityRepository.save(orderTraceabilityEntityMapper.toEntity(orderTraceability));
        return orderTraceabilityEntityMapper.toOrderTraceability(orderTraceabilityEntity);
    }

    @Override
    public List<OrderTraceability> findByOrderIdAndClientId(Long orderId, Long clientId) {
        List<OrderTraceabilityEntity> orderTraceabilityEntityList = orderTraceabilityRepository.findByOrderIdAndClientId(orderId,clientId);
        if (orderTraceabilityEntityList.isEmpty()) {
            throw new TraceabilityNotFoundException();
        }
        return orderTraceabilityEntityMapper.toOrderTraceabilityList(orderTraceabilityEntityList);
    }

    @Override
    public List<OrderTraceability> findByOrderIdsAndStatus(List<Long> orderIds, String status) {

        List<OrderTraceabilityEntity> entities =
                orderTraceabilityRepository.findByOrderIdInAndNewStatus(orderIds, status);

        if (entities.isEmpty()) {
            return Collections.emptyList();
        }

        return orderTraceabilityEntityMapper.toOrderTraceabilityList(entities);
    }
}

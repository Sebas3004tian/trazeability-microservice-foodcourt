package com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.repository;

import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.entity.OrderTraceabilityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IOrderTraceabilityRepository extends MongoRepository<OrderTraceabilityEntity, String> {
    List<OrderTraceabilityEntity> findByOrderIdAndClientId(Long orderId, Long clientId);
    List<OrderTraceabilityEntity> findByOrderIdInAndNewStatus(List<Long> orderIds, String status);
}

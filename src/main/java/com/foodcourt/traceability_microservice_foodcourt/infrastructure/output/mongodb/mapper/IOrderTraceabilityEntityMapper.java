package com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.mapper;

import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.entity.OrderTraceabilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderTraceabilityEntityMapper {

    OrderTraceabilityEntity toEntity(OrderTraceability orderTraceability);
    OrderTraceability toOrderTraceability(OrderTraceabilityEntity orderTraceabilityEntity);
    List<OrderTraceability> toOrderTraceabilityList(List<OrderTraceabilityEntity> orderTraceabilityEntityList);
}

package com.foodcourt.traceability_microservice_foodcourt.application.mapper;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.request.OrderTraceabilityRequestDto;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderTraceabilityRequestMapper {

    OrderTraceability toOrderTraceability(OrderTraceabilityRequestDto orderTraceabilityRequestDto);
}

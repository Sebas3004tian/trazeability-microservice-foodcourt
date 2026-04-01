package com.foodcourt.traceability_microservice_foodcourt.application.mapper;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderTraceabilityResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderTraceabilityResponseMapper {

    OrderTraceabilityResponseDto toResponse(OrderTraceability orderTraceability);

    List<OrderTraceabilityResponseDto> toResponseList(List<OrderTraceability> orderTraceabilityList);
}
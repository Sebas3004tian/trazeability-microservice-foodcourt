package com.foodcourt.traceability_microservice_foodcourt.application.mapper;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderEfficiency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEfficiencyResponseMapper {
    
   OrderEfficiencyResponseDto toResponse(OrderEfficiency orderEfficiency);

    List<OrderEfficiencyResponseDto> toResponseList(List<OrderEfficiency> orderEfficiencyList);
}
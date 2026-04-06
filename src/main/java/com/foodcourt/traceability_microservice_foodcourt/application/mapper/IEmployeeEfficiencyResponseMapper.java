package com.foodcourt.traceability_microservice_foodcourt.application.mapper;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.EmployeeEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.EmployeeEfficiency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEfficiencyResponseMapper {

    EmployeeEfficiencyResponseDto toResponse(EmployeeEfficiency employeeEfficiency);

    List<EmployeeEfficiencyResponseDto> toResponseList(List<EmployeeEfficiency> employeeEfficiencyList);
}
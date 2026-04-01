package com.foodcourt.traceability_microservice_foodcourt.application.handler;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.request.OrderTraceabilityRequestDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.EmployeeEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderTraceabilityResponseDto;

import java.util.List;

public interface IOrderTraceabilityHandler {
    void saveOrderTraceability(OrderTraceabilityRequestDto orderTraceabilityRequestDto);

    List<OrderTraceabilityResponseDto> getOrderTraceability(Long orderId);
    List<OrderEfficiencyResponseDto> getOrderEfficiency(List<Long> orderIds);

    List<EmployeeEfficiencyResponseDto> getEmployeeRanking(List<Long> orderIds);
}

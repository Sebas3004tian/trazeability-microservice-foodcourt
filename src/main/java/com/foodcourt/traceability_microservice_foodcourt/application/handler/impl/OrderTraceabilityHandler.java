package com.foodcourt.traceability_microservice_foodcourt.application.handler.impl;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.request.OrderTraceabilityRequestDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.EmployeeEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderTraceabilityResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.handler.IOrderTraceabilityHandler;
import com.foodcourt.traceability_microservice_foodcourt.application.mapper.IEmployeeEfficiencyResponseMapper;
import com.foodcourt.traceability_microservice_foodcourt.application.mapper.IOrderEfficiencyResponseMapper;
import com.foodcourt.traceability_microservice_foodcourt.application.mapper.IOrderTraceabilityRequestMapper;
import com.foodcourt.traceability_microservice_foodcourt.application.mapper.IOrderTraceabilityResponseMapper;
import com.foodcourt.traceability_microservice_foodcourt.domain.api.IJwtServicePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.api.IOrderTraceabilityServicePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderTraceabilityHandler implements IOrderTraceabilityHandler {

    private final IOrderTraceabilityServicePort orderTraceabilityServicePort;

    private final IOrderTraceabilityRequestMapper orderTraceabilityRequestMapper;
    private final IOrderTraceabilityResponseMapper orderResponseMapper;
    private final IOrderEfficiencyResponseMapper orderEfficiencyResponseMapper;
    private final IEmployeeEfficiencyResponseMapper employeeEfficiencyResponseMapper;

    private final IJwtServicePort jwtServicePort;

    @Override
    public void saveOrderTraceability(OrderTraceabilityRequestDto orderTraceabilityRequestDto) {
        OrderTraceability orderTraceability = orderTraceabilityRequestMapper.toOrderTraceability(orderTraceabilityRequestDto);
        orderTraceabilityServicePort.saveTraceability(orderTraceability);
    }

    @Override
    public List<OrderTraceabilityResponseDto> getOrderTraceability(Long orderId) {
        Long clientId = jwtServicePort.getAuthenticatedUserId();
        return orderResponseMapper.toResponseList(orderTraceabilityServicePort.getTraceability(orderId,clientId));
    }

    @Override
    public List<OrderEfficiencyResponseDto> getOrderEfficiency(List<Long> orderIds) {
        return orderEfficiencyResponseMapper.toResponseList(orderTraceabilityServicePort.getOrderEfficiency(orderIds));
    }

    @Override
    public List<EmployeeEfficiencyResponseDto> getEmployeeRanking(List<Long> orderIds) {
        return employeeEfficiencyResponseMapper.toResponseList(orderTraceabilityServicePort.getEmployeeRanking(orderIds));
    }
}

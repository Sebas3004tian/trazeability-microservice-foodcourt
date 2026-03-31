package com.foodcourt.traceability_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderTraceabilityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OrderTraceabilityRestController {

    private final IOrderTraceabilityHandler orderTraceabilityHandler;

    @GetMapping("/{orderId}")
    public List<OrderTraceabilityResponseDto> getOrderTraceability(@PathVariable Long orderId){

        return //AQui voy
    }
}

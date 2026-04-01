package com.foodcourt.traceability_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.request.OrderTraceabilityRequestDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderTraceabilityResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.handler.IOrderTraceabilityHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traceability")
@RequiredArgsConstructor
public class OrderTraceabilityRestController {

    private final IOrderTraceabilityHandler orderTraceabilityHandler;

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderTraceabilityResponseDto>> getOrderTraceability(@PathVariable Long orderId){
        return ResponseEntity.ok(orderTraceabilityHandler.getOrderTraceability(orderId));
    }

    @PreAuthorize("hasAnyRole('PROPIETARIO', 'EMPLEADO', 'CLIENTE')")
    @PostMapping("/")
    public ResponseEntity<Void> saveOrderTraceability(@Valid @RequestBody OrderTraceabilityRequestDto orderTraceabilityRequestDto){
        orderTraceabilityHandler.saveOrderTraceability(orderTraceabilityRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

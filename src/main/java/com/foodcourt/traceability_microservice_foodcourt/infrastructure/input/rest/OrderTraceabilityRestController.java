package com.foodcourt.traceability_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.traceability_microservice_foodcourt.application.dto.request.OrderTraceabilityRequestDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.EmployeeEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderEfficiencyResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.dto.response.OrderTraceabilityResponseDto;
import com.foodcourt.traceability_microservice_foodcourt.application.handler.IOrderTraceabilityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get traceability documents of a specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receive all orders"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied")
    })
    public ResponseEntity<List<OrderTraceabilityResponseDto>> getOrderTraceability(@PathVariable Long orderId){
        return ResponseEntity.ok(orderTraceabilityHandler.getOrderTraceability(orderId));
    }

    @PreAuthorize("hasAnyRole('PROPIETARIO', 'EMPLEADO', 'CLIENTE')")
    @PostMapping("/")
    @Operation(summary = "save traceability of a specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20a", description = "Traceability saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    public ResponseEntity<Void> saveOrderTraceability(@Valid @RequestBody OrderTraceabilityRequestDto orderTraceabilityRequestDto){
        orderTraceabilityHandler.saveOrderTraceability(orderTraceabilityRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/efficiency/orders")
    @Operation(summary = "Get efficiency of the orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20a", description = "Receive efficiency of orders of the restaurant"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied")
    })
    public ResponseEntity<List<OrderEfficiencyResponseDto>> getOrderEfficiency(@RequestParam List<Long> orderIds){
        return ResponseEntity.ok(orderTraceabilityHandler.getOrderEfficiency(orderIds));
    }

    @GetMapping("/efficiency/employees")
    @Operation(summary = "Get efficiency of the employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20a", description = "Receive efficiency of employees of the restaurant"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied")
    })
    public ResponseEntity<List<EmployeeEfficiencyResponseDto>> getEmployeesRanking(@RequestParam List<Long> orderIds) {
        return ResponseEntity.ok(orderTraceabilityHandler.getEmployeeRanking(orderIds));
    }


}

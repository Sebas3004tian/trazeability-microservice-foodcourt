package com.foodcourt.traceability_microservice_foodcourt;


import com.foodcourt.traceability_microservice_foodcourt.domain.model.EmployeeEfficiency;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderStatus;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.usecase.OrderTraceabilityUseCase;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.exception.TraceabilityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEmployeeRankingUseCaseTest {

    @Mock
    private IOrderTraceabilityPersistencePort persistencePort;

    @InjectMocks
    private OrderTraceabilityUseCase useCase;

    @Test
    void shouldReturnEmployeeRanking() {
        List<Long> orderIds = List.of(1L, 2L);

        LocalDateTime now = LocalDateTime.now();

        List<OrderTraceability> pendingList = List.of(
                new OrderTraceability(null, 1L, null, null,
                        now.minusMinutes(10),
                        null, OrderStatus.PENDIENTE.name(),
                        101L, null),

                new OrderTraceability(null, 2L, null, null,
                        now.minusMinutes(20),
                        null, OrderStatus.PENDIENTE.name(),
                        102L, null)
        );

        List<OrderTraceability> readyList = List.of(
                new OrderTraceability(null, 1L, null, null,
                        now,
                        null, OrderStatus.LISTO.name(),
                        101L, null),

                new OrderTraceability(null, 2L, null, null,
                        now,
                        null, OrderStatus.LISTO.name(),
                        102L, null)
        );

        when(persistencePort.findByOrderIdsAndStatus(orderIds, OrderStatus.PENDIENTE.name()))
                .thenReturn(pendingList);

        when(persistencePort.findByOrderIdsAndStatus(orderIds, OrderStatus.LISTO.name()))
                .thenReturn(readyList);

        List<EmployeeEfficiency> result = useCase.getEmployeeRanking(orderIds);

        assertEquals(2, result.size());

        assertEquals(101L, result.get(0).getEmployeeId());
        assertEquals(1, result.get(0).getTotalOrders());

        assertEquals(102L, result.get(1).getEmployeeId());
        assertEquals(1, result.get(1).getTotalOrders());
    }

    @Test
    void shouldThrowExceptionWhenNoData() {
        List<Long> orderIds = List.of(1L);

        when(persistencePort.findByOrderIdsAndStatus(orderIds, OrderStatus.PENDIENTE.name()))
                .thenReturn(List.of());

        when(persistencePort.findByOrderIdsAndStatus(orderIds, OrderStatus.LISTO.name()))
                .thenReturn(List.of());

        assertThrows(TraceabilityNotFoundException.class,
                () -> useCase.getEmployeeRanking(orderIds));
    }
}
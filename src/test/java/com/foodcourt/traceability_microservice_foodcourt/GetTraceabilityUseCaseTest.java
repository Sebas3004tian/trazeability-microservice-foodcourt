package com.foodcourt.traceability_microservice_foodcourt;


import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.usecase.OrderTraceabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTraceabilityUseCaseTest {

    @Mock
    private IOrderTraceabilityPersistencePort persistencePort;

    @InjectMocks
    private OrderTraceabilityUseCase useCase;

    @Test
    void shouldReturnTraceabilityList() {
        Long orderId = 1L;
        Long clientId = 2L;

        List<OrderTraceability> expectedList = List.of(new OrderTraceability());

        when(persistencePort.findByOrderIdAndClientId(orderId, clientId))
                .thenReturn(expectedList);

        List<OrderTraceability> result = useCase.getTraceability(orderId, clientId);

        assertEquals(expectedList, result);
        verify(persistencePort).findByOrderIdAndClientId(orderId, clientId);
    }
}
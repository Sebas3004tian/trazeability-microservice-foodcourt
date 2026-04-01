package com.foodcourt.traceability_microservice_foodcourt;


import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.usecase.OrderTraceabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveTraceabilityUseCaseTest {

    @Mock
    private IOrderTraceabilityPersistencePort persistencePort;

    @InjectMocks
    private OrderTraceabilityUseCase useCase;

    @Captor
    private ArgumentCaptor<OrderTraceability> captor;

    @Test
    void shouldSaveTraceabilityWithCurrentDate() {

        OrderTraceability traceability = new OrderTraceability();
        traceability.setOrderId(1L);
        traceability.setClientId(2L);

        useCase.saveTraceability(traceability);

        verify(persistencePort).saveTraceability(captor.capture());

        OrderTraceability saved = captor.getValue();

        assertNotNull(saved.getDate());
        assertEquals(1L, saved.getOrderId());
        assertEquals(2L, saved.getClientId());
    }
}
package com.foodcourt.traceability_microservice_foodcourt.infrastructure.configuration;

import com.foodcourt.traceability_microservice_foodcourt.domain.api.IJwtServicePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.api.IOrderTraceabilityServicePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.usecase.OrderTraceabilityUseCase;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.adapter.OrderTraceabilityMongodbAdapter;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.mapper.IOrderTraceabilityEntityMapper;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.mongodb.repository.IOrderTraceabilityRepository;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.output.security.adapter.JwtServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IOrderTraceabilityRepository orderTraceabilityRepository;

    private final IOrderTraceabilityEntityMapper orderTraceabilityEntityMapper;

    @Bean
    public IJwtServicePort jwtServicePort(){
        return new JwtServiceAdapter();
    }

    @Bean
    public IOrderTraceabilityPersistencePort orderTraceabilityPersistencePort(){
        return new OrderTraceabilityMongodbAdapter(orderTraceabilityRepository,orderTraceabilityEntityMapper);
    }

    @Bean
    public IOrderTraceabilityServicePort orderTraceabilityServicePort(){
        return new OrderTraceabilityUseCase(
                orderTraceabilityPersistencePort()
        );
    }
}

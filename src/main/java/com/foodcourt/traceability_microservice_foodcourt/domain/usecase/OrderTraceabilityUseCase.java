package com.foodcourt.traceability_microservice_foodcourt.domain.usecase;

import com.foodcourt.traceability_microservice_foodcourt.domain.api.IOrderTraceabilityServicePort;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.EmployeeEfficiency;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderEfficiency;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderStatus;
import com.foodcourt.traceability_microservice_foodcourt.domain.model.OrderTraceability;
import com.foodcourt.traceability_microservice_foodcourt.domain.spi.IOrderTraceabilityPersistencePort;
import com.foodcourt.traceability_microservice_foodcourt.infrastructure.exception.TraceabilityNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderTraceabilityUseCase implements IOrderTraceabilityServicePort {

    private final IOrderTraceabilityPersistencePort persistencePort;

    public OrderTraceabilityUseCase(IOrderTraceabilityPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void saveTraceability(OrderTraceability orderTraceability) {
        orderTraceability.setDate(LocalDateTime.now());
        persistencePort.saveTraceability(orderTraceability);
    }

    @Override
    public List<OrderTraceability> getTraceability(Long orderId, Long clientId) {
        return persistencePort.findByOrderIdAndClientId(orderId, clientId);
    }

    @Override
    public List<OrderEfficiency> getOrderEfficiency(List<Long> orderIds) {

        List<OrderTraceability> pendingList =
                persistencePort.findByOrderIdsAndStatus(orderIds, OrderStatus.PENDIENTE.name());

        List<OrderTraceability> readyList =
                persistencePort.findByOrderIdsAndStatus(orderIds, OrderStatus.LISTO.name());

        if(pendingList.isEmpty() || readyList.isEmpty()){
            throw new TraceabilityNotFoundException();
        }

        List<OrderEfficiency> orderEfficiencyList = new ArrayList<>();

        for (OrderTraceability pending : pendingList) {

            for (OrderTraceability ready : readyList) {

                if (pending.getOrderId().equals(ready.getOrderId())) {

                    orderEfficiencyList.add(new OrderEfficiency(
                            pending.getOrderId(),
                            ready.getEmployeeId(),
                            pending.getDate(),
                            ready.getDate()
                    ));

                    break;
                }
            }
        }

        return orderEfficiencyList;
    }

    @Override
    public List<EmployeeEfficiency> getEmployeeRanking(List<Long> orderIds) {

        List<OrderEfficiency> orderEfficiencies = getOrderEfficiency(orderIds);

        if (orderEfficiencies.isEmpty()) {
            throw new TraceabilityNotFoundException();
        }

        List<EmployeeEfficiency> employeeEfficiencyList = new ArrayList<>();

        for (OrderEfficiency order : orderEfficiencies) {

            Long employeeId = order.getEmployeeId();
            Duration duration = order.getDuration();

            EmployeeEfficiency existing = null;

            for (EmployeeEfficiency employeeEfficiency : employeeEfficiencyList) {
                if (employeeEfficiency.getEmployeeId().equals(employeeId)) {
                    existing = employeeEfficiency;
                    break;
                }
            }

            if (existing == null) {
                employeeEfficiencyList.add(new EmployeeEfficiency(
                        employeeId,
                        1,
                        duration
                ));
            } else {
                existing.setTotalOrders(existing.getTotalOrders() + 1);
                existing.setAverageDuration(
                        existing.getAverageDuration().plus(duration)
                );
            }
        }

        for (EmployeeEfficiency employeeEfficiency : employeeEfficiencyList) {

            Duration total = employeeEfficiency.getAverageDuration();
            int count = employeeEfficiency.getTotalOrders();

            employeeEfficiency.setAverageDuration(total.dividedBy(count));
        }

        employeeEfficiencyList.sort(Comparator.comparing(EmployeeEfficiency::getAverageDuration));

        return employeeEfficiencyList;
    }
}

package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.common.command.OrderCommand;
import com.revolution.common.response.OrderResponse;
import com. revolution. common. response.LineItemResponse;
import com.revolution.payment.service.api.port.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceAdapter implements OrderService {

    @Override
    public OrderResponse getOrder(OrderCommand command) {
        return new OrderResponse(1L, 1L, List.of(
                new LineItemResponse("PRODUKT 1", BigDecimal.ONE),
                new LineItemResponse("PRODUKT 2", BigDecimal.ONE)
        ));
    }
}

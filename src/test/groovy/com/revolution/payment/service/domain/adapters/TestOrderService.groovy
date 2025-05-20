package com.revolution.payment.service.domain.adapters

import com.revolution.common.command.OrderCommand
import com.revolution.common.response.OrderResponse
import com.revolution.payment.service.api.port.OrderService

class TestOrderService implements OrderService{
    @Override
    OrderResponse getOrder(OrderCommand command) {
        new OrderResponse(1L, 1L, List.of())
    }
}

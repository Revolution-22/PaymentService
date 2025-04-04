package com.revolution.payment.service.domain;

import com.revolution.common.command.OrderCommand;
import com.revolution.common.response.OrderResponse;
import com.revolution.payment.service.api.command.LineItemCommand;
import com.revolution.payment.service.api.command.PaymentCommand;
import com.revolution.payment.service.api.request.PaymentRequest;

class CommandMapper {

    PaymentCommand toCommand(OrderResponse response) {
        return new PaymentCommand(response.orderId(), response.receiverId(), response.items().stream()
                .map(item -> new LineItemCommand(item.name(), item.price())).toList());
    }

    OrderCommand toCommand(PaymentRequest request) {
        return new OrderCommand(request.orderId(), request.receiverId());
    }
}

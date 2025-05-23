package com.revolution.payment.service.api.command;

import java.util.List;

public record PaymentCommand (
        long transactionId,
        long orderId,
        long receiverId,
        List<LineItemCommand> items
) {
}

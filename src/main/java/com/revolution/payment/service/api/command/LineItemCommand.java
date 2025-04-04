package com.revolution.payment.service.api.command;

import java.math.BigDecimal;

public record LineItemCommand(
        String name,
        BigDecimal price
) {
}

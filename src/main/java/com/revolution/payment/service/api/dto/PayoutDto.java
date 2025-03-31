package com.revolution.payment.service.api.dto;

public record PayoutDto (
        String bankAccountNumber,
        int status
) {
}

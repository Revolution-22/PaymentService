package com.revolution.payment.service.domain;

import com.revolution.payment.service.api.dto.PaymentDto;

import java.util.Arrays;
import java.util.Objects;

class PaymentMapper {

    PaymentDto toDto(Payment payment) {
        return new PaymentDto(payment.getTransactionId(), payment.getOrderId(), payment.getReceiverId(), payment.getStatus().getCode());
    }

    Payment toModel(PaymentDto paymentDto) {
        return new Payment(paymentDto.transactionId(), paymentDto.orderId(), paymentDto.receiverId(), getStatus(paymentDto.status()));
    }

    PaymentStatus getStatus(int status) {
        return Arrays.stream(PaymentStatus.values())
                .filter(paymentStatus -> Objects.equals(paymentStatus.getCode(), status))
                .findFirst()
                .orElse(PaymentStatus.IN_PROGRESS);
    }
}

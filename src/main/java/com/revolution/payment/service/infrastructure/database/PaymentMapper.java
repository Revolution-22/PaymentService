package com.revolution.payment.service.infrastructure.database;

import com.revolution.payment.service.api.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
class PaymentMapper {

    PaymentDto toDto(PaymentEntity paymentEntity) {
        return new PaymentDto(paymentEntity.getTransactionId(), paymentEntity.getOrderId(), paymentEntity.getReceiverId(), paymentEntity.getStatus());
    }

    PaymentEntity toEntity(PaymentDto paymentDto) {
        return new PaymentEntity(paymentDto.transactionId(), paymentDto.orderId(), paymentDto.receiverId(), paymentDto.status());
    }
}

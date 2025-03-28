package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.dto.PaymentDto
import com.revolution.payment.service.api.port.PaymentRepository

class TestPaymentRepository implements PaymentRepository {

    protected Map<Long, PaymentDto> database = new HashMap<>()

    @Override
    PaymentDto save(PaymentDto paymentDto) {
        long transactionId = paymentDto.transactionId() == null ? database.size() : paymentDto.transactionId()
        PaymentDto paymentDtoWithId = new PaymentDto(transactionId, paymentDto.orderId(), paymentDto.receiverId(), paymentDto.status())
        database.put(transactionId, paymentDtoWithId)
        paymentDtoWithId
    }

    @Override
    Optional<PaymentDto> findByTransactionId(long transactionId) {
        Optional.ofNullable(database.get(transactionId))
    }
}

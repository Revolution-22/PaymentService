package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.dto.PaymentDto
import com.revolution.payment.service.api.port.PaymentRepository

class TestPaymentRepository implements PaymentRepository {

    protected Map<Long, PaymentDto> database = new HashMap<>()

    @Override
    PaymentDto save(PaymentDto paymentDto) {
        long id = paymentDto.id() == null ? database.size() : paymentDto.id()
        PaymentDto paymentDtoWithId = new PaymentDto(id, paymentDto.orderId(), paymentDto.receiverId(), paymentDto.status())
        database.put(id, paymentDtoWithId)
    }

    @Override
    Optional<PaymentDto> findByOrderIdAndReceiverId(long orderId, long receiverId) {
        database.values().stream()
            .filter {it.receiverId() == receiverId && it.orderId() == orderId}
            .findFirst()
    }
}

package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.dto.PaymentDto;

import java.util.Optional;

public interface PaymentRepository {

    PaymentDto save(PaymentDto paymentDto);

    Optional<PaymentDto> findByOrderIdAndReceiverId(long orderId, long receiverId);
}

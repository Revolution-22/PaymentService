package com.revolution.payment.service.domain;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.PaymentRepository;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.request.PaymentStatusRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CorePaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto initPayment(PaymentRequest request) {
        return null;
    }

    @Override
    public PaymentDto markStatus(PaymentStatusRequest request) {
        return null;
    }
}

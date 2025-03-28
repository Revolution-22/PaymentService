package com.revolution.payment.service.domain;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.exception.PaymentNotFoundException;
import com.revolution.payment.service.api.port.PaymentRepository;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.request.PaymentStatusRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CorePaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto initPayment(PaymentRequest request) {
        Payment payment = Payment.ofInProgress(request.orderId(), request.receiverId());
        return paymentRepository.save(paymentMapper.toDto(payment));
    }

    @Override
    public PaymentDto markStatus(PaymentStatusRequest request) {
        Payment payment = paymentRepository.findByTransactionId(request.transactionId())
                .map(paymentMapper::toModel)
                .orElseThrow(() -> new PaymentNotFoundException(request.transactionId()));
        payment.setStatus(paymentMapper.getStatus(request.status()));
        return paymentRepository.save(paymentMapper.toDto(payment));
    }
}

package com.revolution.payment.service.domain;

import com.revolution.common.event.PaymentEvent;
import com.revolution.common.event.Topics;
import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.port.PaymentFacade;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.request.PaymentStatusRequest;
import com.revolution.payment.service.api.request.PayoutRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CorePaymentFacade implements PaymentFacade {

    private final PaymentService paymentService;
    private final BrokerService brokerService;
    private final ProviderService providerService;
    private final BankService bankService;

    @Override
    public LinkResponse generatePaymentLink(PaymentRequest request) {
        LinkResponse response = providerService.generatePaymentLink(request);
        paymentService.initPayment(request);
        return response;
    }

    @Override
    public PaymentDto handlePayment(String payload) {
        PaymentDto paymentDto = providerService.handlePayment(payload);
        PaymentDto savedPaymentDto = paymentService.markStatus(new PaymentStatusRequest(paymentDto.transactionId(), paymentDto.status()));
        brokerService.publishMessage(Topics.PAYMENT_TOPIC, new PaymentEvent(savedPaymentDto.orderId(), savedPaymentDto.receiverId(), savedPaymentDto.status()));
        return savedPaymentDto;
    }

    @Override
    public PayoutDto makePayout(PayoutRequest request) {
        return bankService.makePayout(request);
    }
}

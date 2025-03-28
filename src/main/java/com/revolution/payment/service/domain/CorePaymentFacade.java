package com.revolution.payment.service.domain;

import com.revolution.common.event.PaymentEvent;
import com.revolution.common.event.Topics;
import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.port.PaymentFacade;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.request.PaymentStatusRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CorePaymentFacade implements PaymentFacade {

    private final PaymentService paymentService;
    private final BrokerService brokerService;
    private final ProviderService providerService;

    @Override
    public LinkResponse generatePaymentLink(PaymentRequest request) {
        LinkResponse response = providerService.generatePaymentLink(request);
        PaymentDto paymentDto = paymentService.initPayment(request);
        brokerService.publishMessage(Topics.PAYMENT_TOPIC, new PaymentEvent(paymentDto.orderId(), paymentDto.receiverId(), paymentDto.status()));
        return response;
    }

    @Override
    public PaymentDto handlePayment(String payload) {
        PaymentDto paymentDto = providerService.handlePayment(payload);
        return paymentService.markStatus(new PaymentStatusRequest(paymentDto.transactionId(), paymentDto.status()));
    }
}

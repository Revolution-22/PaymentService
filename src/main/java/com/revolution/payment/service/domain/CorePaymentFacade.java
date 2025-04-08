package com.revolution.payment.service.domain;

import com.revolution.common.event.PaymentEvent;
import com.revolution.common.event.Topics;
import com.revolution.common.response.OrderResponse;
import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.port.OrderService;
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
    private final OrderService orderService;
    private final CommandMapper commandMapper;

    @Override
    public LinkResponse generatePaymentLink(PaymentRequest request) {
        PaymentDto paymentDto = paymentService.initPayment(request);
        OrderResponse orderResponse = orderService.getOrder(commandMapper.toCommand(request));
        return providerService.generatePaymentLink(commandMapper.toCommand(orderResponse, paymentDto.transactionId()));
    }

    @Override
    public PaymentDto handlePayment(String payload, String sigHeader) {
        PaymentDto paymentDto = providerService.handlePayment(payload, sigHeader);
        PaymentDto savedPaymentDto = paymentService.markStatus(new PaymentStatusRequest(paymentDto.transactionId(), paymentDto.status()));
        brokerService.publishMessage(Topics.PAYMENT_TOPIC, new PaymentEvent(savedPaymentDto.orderId(), savedPaymentDto.receiverId(), savedPaymentDto.status()));
        return savedPaymentDto;
    }

    @Override
    public PayoutDto notifyPayout(PayoutRequest request) {
        return bankService.notifyPayout(request);
    }
}

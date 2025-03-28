package com.revolution.payment.service.domain;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.port.PaymentFacade;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CorePaymentFacade implements PaymentFacade {

    private final PaymentService paymentService;
    private final BrokerService brokerService;
    private final ProviderService providerService;

    @Override
    public LinkResponse generatePaymentLink(PaymentRequest request) {
        return null;
    }

    @Override
    public PaymentDto handlePayment(String payload) {
        return null;
    }
}

package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceAdapter implements ProviderService {

    @Override
    public LinkResponse generatePaymentLink(PaymentRequest request) {
        return null;
    }

    @Override
    public PaymentDto handlePayment(String payload) {
        return null;
    }
}

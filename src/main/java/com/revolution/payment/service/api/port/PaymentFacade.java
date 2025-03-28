package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.response.LinkResponse;
import com.revolution.payment.service.api.request.PaymentRequest;

public interface PaymentFacade {

    LinkResponse generatePaymentLink(PaymentRequest request);

    PaymentDto handlePayment(String payload);
}

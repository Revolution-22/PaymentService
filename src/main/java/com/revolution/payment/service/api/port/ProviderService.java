package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.command.PaymentCommand;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import com.revolution.payment.service.api.dto.PaymentDto;

public interface ProviderService {

    LinkResponse generatePaymentLink(PaymentCommand command);

    PaymentDto handlePayment(String payload, String sigHeader);
}

package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.response.LinkResponse;
import com.revolution.payment.service.api.dto.PaymentDto;

public interface ProviderService {

    LinkResponse generatePaymentLink(long orderId, long receiverId);

    PaymentDto handlePayment(String payload);
}

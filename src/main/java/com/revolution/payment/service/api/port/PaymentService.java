package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.request.PaymentStatusRequest;

public interface PaymentService {

    PaymentDto initPayment(PaymentRequest request);

    PaymentDto markStatus(PaymentStatusRequest request);
}

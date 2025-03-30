package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.request.PayoutRequest;

public interface BankService {

    PayoutDto makePayout(PayoutRequest request);
}

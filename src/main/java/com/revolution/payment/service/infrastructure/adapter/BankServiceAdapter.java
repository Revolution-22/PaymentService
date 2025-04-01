package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.request.PayoutRequest;
import org.springframework.stereotype.Service;

@Service
public class BankServiceAdapter implements BankService {

    @Override
    public PayoutDto makePayout(PayoutRequest request) {
        return null;
    }
}

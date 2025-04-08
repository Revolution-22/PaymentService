package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.dto.PayoutDto
import com.revolution.payment.service.api.port.BankService
import com.revolution.payment.service.api.request.PayoutRequest

class TestBankService implements BankService {

    @Override
    PayoutDto notifyPayout(PayoutRequest request) {
        new PayoutDto(request.bankAccountNumber(), 1)
    }
}

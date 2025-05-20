package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.dto.PayoutDto
import com.revolution.payment.service.api.port.AdminService
import com.revolution.payment.service.api.request.PayoutRequest

class TestAdminService implements AdminService {

    @Override
    PayoutDto notifyPayout(PayoutRequest request) {
        new PayoutDto(request.bankAccountNumber(), 1)
    }
}

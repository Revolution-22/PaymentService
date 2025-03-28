package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.dto.PaymentDto
import com.revolution.payment.service.api.port.ProviderService
import com.revolution.payment.service.api.request.PaymentRequest
import com.revolution.payment.service.api.response.LinkResponse
import com.revolution.payment.service.domain.Constants

class TestProviderService implements ProviderService, Constants {

    @Override
    LinkResponse generatePaymentLink(PaymentRequest request) {
        new LinkResponse(LINK
                .concat("?orderId=")
                .concat(request.orderId() as String)
                .concat("&recevierId=")
                .concat(request.receiverId() as String)
        )
    }

    @Override
    PaymentDto handlePayment(String payload) {
        String[] payloads = payload.split(";")
        new PaymentDto(Long.parseLong(payloads[0]), Long.parseLong(payloads[1]), Long.parseLong(payloads[2]), Integer.parseInt(payloads[3]))
    }
}

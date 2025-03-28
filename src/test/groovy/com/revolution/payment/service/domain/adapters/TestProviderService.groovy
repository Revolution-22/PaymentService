package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.dto.PaymentDto
import com.revolution.payment.service.api.port.ProviderService
import com.revolution.payment.service.api.response.LinkResponse
import com.revolution.payment.service.domain.Constants

class TestProviderService implements ProviderService, Constants {

    @Override
    LinkResponse generatePaymentLink(long orderId, long receiverId) {
        new LinkResponse(LINK
                .concat("?orderId=")
                .concat(orderId)
                .concat("&recevierId=")
                .concat(receiverId)
        )
    }

    @Override
    PaymentDto handlePayment(String payload) {
        String[] payloads = payload.split(";")
        new PaymentDto(Long.parseLong(payloads[0]), Long.parseLong(payloads[1]), Long.parseLong(payloads[2]), Integer.parseInt(payloads[3]))
    }
}

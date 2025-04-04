package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.command.PaymentCommand
import com.revolution.payment.service.api.dto.PaymentDto
import com.revolution.payment.service.api.port.ProviderService
import com.revolution.payment.service.api.response.LinkResponse
import com.revolution.payment.service.domain.Constants

class TestProviderService implements ProviderService, Constants {

    @Override
    LinkResponse generatePaymentLink(PaymentCommand command) {
        new LinkResponse(LINK
                .concat("?orderId=")
                .concat(command.orderId() as String)
                .concat("&recevierId=")
                .concat(command.receiverId() as String)
        )
    }

    @Override
    PaymentDto handlePayment(String payload, String sigHeader) {
        String[] payloads = payload.split(";")
        new PaymentDto(Long.parseLong(payloads[0]), Long.parseLong(payloads[1]), Long.parseLong(payloads[2]), Integer.parseInt(payloads[3]))
    }
}

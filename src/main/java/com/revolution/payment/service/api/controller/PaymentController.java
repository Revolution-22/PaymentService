package com.revolution.payment.service.api.controller;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.PaymentFacade;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentController {

    private final PaymentFacade paymentFacade;

    @PostMapping("/links")
    LinkResponse generatePaymentLink(@RequestBody PaymentRequest request) {
        return paymentFacade.generatePaymentLink(request);
    }

    @PostMapping("/handlers")
    PaymentDto handlePayment(@RequestBody String payload, HttpServletRequest request) {
        return paymentFacade.handlePayment(payload, request.getHeader("Stripe-Signature"));
    }
}

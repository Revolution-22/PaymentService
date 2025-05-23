package com.revolution.payment.service.domain

import com.revolution.payment.service.api.dto.PaymentDto
import com.revolution.payment.service.api.exception.PaymentNotFoundException
import com.revolution.payment.service.api.port.PaymentFacade
import com.revolution.payment.service.api.request.PaymentRequest
import com.revolution.payment.service.api.request.PayoutRequest
import com.revolution.payment.service.domain.adapters.TestBeanConfiguration
import spock.lang.Specification
import spock.lang.Subject

class CorePaymentFacadeSpec extends Specification implements Constants {

    private TestBeanConfiguration configuration = new TestBeanConfiguration()

    @Subject
    private PaymentFacade paymentFacade = configuration.paymentFacade()

    def setup(){
        configuration.clear()
    }

    def "should not handle payment" () {
        when: "Try handle payment"
            paymentFacade.handlePayment("1;1;1;2", SIG_HEADER)
        then: "Check if exception thrown"
            thrown(PaymentNotFoundException)
    }

    def "should process payment as success" () {
        given: "Generate link and create payment"
            paymentFacade.generatePaymentLink(new PaymentRequest(1L, 1L))
        when: "Handle payment with success status"
            PaymentDto paymentDto = paymentFacade.handlePayment("0;1;1;2", SIG_HEADER)
        then: "Check if data is correct"
            paymentDto.orderId() == 1L
            paymentDto.receiverId() == 1L
            paymentDto.status() == 2
    }

    def "should process payment as failed" () {
        given: "Generate link and create payment"
            paymentFacade.generatePaymentLink(new PaymentRequest(1L, 1L))
        when: "Handle payment with success status"
            PaymentDto paymentDto = paymentFacade.handlePayment("0;1;1;3", SIG_HEADER)
        then: "Check if data is correct"
            paymentDto.orderId() == 1L
            paymentDto.receiverId() == 1L
            paymentDto.status() == 3
    }

    def "should mock payout" () {
        when: "Call mock payout"
            paymentFacade.notifyPayout(new PayoutRequest(BANK_ACCOUNT_NUMBER, 1L, 1L, BigDecimal.ONE))
        then: "Nothing wrong happen"
            notThrown(Exception)
    }
}

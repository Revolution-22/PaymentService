package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.port.PaymentFacade
import com.revolution.payment.service.api.port.PaymentRepository
import com.revolution.payment.service.api.port.PaymentService
import com.revolution.payment.service.domain.PaymentBeanConfiguration

class TestBeanConfiguration {

    private final PaymentBeanConfiguration paymentBeanConfiguration = new PaymentBeanConfiguration()

    private final PaymentRepository paymentRepository = new TestPaymentRepository()

    PaymentFacade paymentFacade() {
        return paymentBeanConfiguration.paymentFacade(paymentService(), new TestBrokerService(), new TestProviderService(), new TestBankService())
    }

    private PaymentService paymentService() {
        return paymentBeanConfiguration.paymentService(paymentRepository)
    }

    def clear() {
        paymentRepository.database.clear()
    }

}

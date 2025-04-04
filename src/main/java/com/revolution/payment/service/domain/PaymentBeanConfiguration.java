package com.revolution.payment.service.domain;

import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.port.OrderService;
import com.revolution.payment.service.api.port.PaymentFacade;
import com.revolution.payment.service.api.port.PaymentRepository;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.port.ProviderService;

public class PaymentBeanConfiguration {

    public PaymentFacade paymentFacade(PaymentService paymentService, BrokerService brokerService, ProviderService providerService, BankService bankService, OrderService orderService) {
        return new CorePaymentFacade(paymentService, brokerService, providerService, bankService, orderService, new CommandMapper());
    }

    public PaymentService paymentService(PaymentRepository paymentRepository) {
        return new CorePaymentService(paymentRepository, new PaymentMapper());
    }
}

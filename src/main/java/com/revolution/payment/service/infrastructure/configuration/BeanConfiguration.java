package com.revolution.payment.service.infrastructure.configuration;

import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.port.OrderService;
import com.revolution.payment.service.api.port.PaymentFacade;
import com.revolution.payment.service.api.port.PaymentRepository;
import com.revolution.payment.service.api.port.PaymentService;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.domain.PaymentBeanConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.revolution.payment.service.infrastructure.*")
public class BeanConfiguration {

    private final PaymentBeanConfiguration paymentBeanConfiguration = new PaymentBeanConfiguration();

    @Bean
    PaymentFacade paymentFacade(PaymentService paymentService, BrokerService brokerService, ProviderService providerService, BankService bankService, OrderService orderService) {
        return paymentBeanConfiguration.paymentFacade(paymentService, brokerService, providerService, bankService, orderService);
    }

    @Bean
    PaymentService paymentService(PaymentRepository paymentRepository) {
        return paymentBeanConfiguration.paymentService(paymentRepository);
    }
}

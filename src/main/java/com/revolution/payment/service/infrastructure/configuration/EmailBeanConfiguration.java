package com.revolution.payment.service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Profile("production")
class EmailBeanConfiguration {

    @Bean
    JavaMailSender javaMailSender(EmailConfiguration configuration) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(configuration.getHost());
        mailSender.setPort(configuration.getPort());

        mailSender.setUsername(configuration.getUsername());
        mailSender.setPassword(configuration.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", configuration.getTransportProtocol());
        props.put("mail.smtp.auth", configuration.getSmtpAuth());
        props.put("mail.smtp.starttls.enable", configuration.getSmtpStarttls());

        return mailSender;
    }
}

package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.port.EmailService;
import com.revolution.payment.service.infrastructure.configuration.EmailConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("production")
class EmailServiceAdapter implements EmailService {

    private final EmailConfiguration configuration;
    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String recipient, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(configuration.getFrom());
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

}

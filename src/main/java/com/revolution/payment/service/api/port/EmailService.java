package com.revolution.payment.service.api.port;

public interface EmailService {

    void sendEmail(String recipient, String subject, String body);
}

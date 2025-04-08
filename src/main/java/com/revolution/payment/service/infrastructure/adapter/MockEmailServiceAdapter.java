package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.port.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("!production")
class MockEmailServiceAdapter implements EmailService {

    @Override
    public void sendEmail(String recipient, String subject, String body) {
      log.info("""
              Sent email to %s \n
              With subject %s \n
              With body %s
              """.formatted(recipient, subject, body));
    }
}

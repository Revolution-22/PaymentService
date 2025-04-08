package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.port.EmailService;
import com.revolution.payment.service.api.request.PayoutRequest;
import com.revolution.payment.service.infrastructure.configuration.EmailConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceAdapter implements BankService {

    private static final String SUBJECT = "Revolution-22 :: Zlecono wyplate!";

    private final EmailService emailService;
    private final EmailConfiguration emailConfiguration;

    @Override
    public PayoutDto notifyPayout(PayoutRequest request) {
        emailService.sendEmail(
                emailConfiguration.getAdmin(),
                SUBJECT,
                """
                        Zlecono wyplate: \n
                        %s
                        """.formatted(request)
        );
        return new PayoutDto(request.bankAccountNumber(), 1);
    }
}

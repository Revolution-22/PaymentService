package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.common.command.PayoutCommand;
import com.revolution.common.event.Topics;
import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.port.BankService;
import com.revolution.payment.service.api.port.BrokerService;
import com.revolution.payment.service.api.request.PayoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceAdapter implements BankService {

    private static final String SUBJECT = "Revolution-22 :: Zlecono wyplate!";

    private final BrokerService brokerService;

    @Override
    public PayoutDto notifyPayout(PayoutRequest request) {
        brokerService.publishMessage(Topics.PAYOUT_TOPIC, new PayoutCommand(request.bankAccountNumber(), request.orderId(), request.receiverId(), request.amount()));
        return new PayoutDto(request.bankAccountNumber(), 1);
    }
}

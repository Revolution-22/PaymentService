package com.revolution.payment.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Payment {

    private Long id;
    private long orderId;
    private long receiverId;
    private PaymentStatus status;
}

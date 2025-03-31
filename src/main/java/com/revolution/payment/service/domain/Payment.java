package com.revolution.payment.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
class Payment {

    private Long transactionId;
    private long orderId;
    private long receiverId;
    @Setter
    private PaymentStatus status;

    static Payment ofInProgress(long orderId, long receiverId) {
        return new Payment(null, orderId, receiverId, PaymentStatus.IN_PROGRESS);
    }
}

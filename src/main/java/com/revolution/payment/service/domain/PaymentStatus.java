package com.revolution.payment.service.domain;

enum PaymentStatus {

    IN_PROGRESS(1),
    PAID(2),
    FAILED(3);

    private int code;

    PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

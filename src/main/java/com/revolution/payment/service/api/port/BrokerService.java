package com.revolution.payment.service.api.port;

public interface BrokerService {

    void publishMessage(String topic, Object message);
}

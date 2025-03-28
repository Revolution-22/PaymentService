package com.revolution.payment.service.domain.adapters

import com.revolution.payment.service.api.port.BrokerService
import groovy.util.logging.Slf4j

@Slf4j
class TestBrokerService implements BrokerService {

    @Override
    void publishMessage(String topic, Object message) {
        log.info("Sending message {} to topic {}", message, topic)
    }
}

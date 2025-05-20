package com.revolution.payment.service.infrastructure.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import static com.revolution.common.event.Topics.REGISTER_TOPIC;

@Configuration
class KafkaTopicConfig {

    @Bean
    KafkaAdmin kafkaAdmin(KafkaProperties kafkaProperties) {
        return new KafkaAdmin(kafkaProperties.buildAdminProperties(null));
    }
    
    @Bean
    NewTopic registerUserTopic() {
        return new NewTopic(REGISTER_TOPIC, 1, (short) 1);
    }
}
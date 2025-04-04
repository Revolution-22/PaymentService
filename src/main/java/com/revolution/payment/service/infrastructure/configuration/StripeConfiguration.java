package com.revolution.payment.service.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "stripe")
@Getter
@Setter
public class StripeConfiguration {

    private String apiKey;
    private String redirectUrl;
    private String secret;
}

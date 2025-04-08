package com.revolution.payment.service.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "email")
@Getter
@Setter
public class EmailConfiguration {

    private String admin;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String transportProtocol;
    private String smtpAuth;
    private String smtpStarttls;
    private String from;
}

package com.revolution.payment.service.infrastructure.database;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
@Builder
@Getter
@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull
    private long orderId;

    @NotNull
    private long receiverId;

    @NotNull
    @ColumnDefault(value = "1")
    private int status;
}

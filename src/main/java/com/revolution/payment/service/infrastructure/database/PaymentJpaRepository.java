package com.revolution.payment.service.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByTransactionId(long transactionId);
}

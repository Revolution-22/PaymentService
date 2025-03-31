package com.revolution.payment.service.infrastructure.database;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        return paymentMapper.toDto(paymentJpaRepository.save(paymentMapper.toEntity(paymentDto)));
    }

    @Override
    public Optional<PaymentDto> findByTransactionId(long transactionId) {
        return paymentJpaRepository.findByTransactionId(transactionId)
                .map(paymentMapper::toDto);
    }
}

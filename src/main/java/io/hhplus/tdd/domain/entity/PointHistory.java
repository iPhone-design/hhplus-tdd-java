package io.hhplus.tdd.domain.entity;

import io.hhplus.tdd.domain.entity.TransactionType;

public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {
}

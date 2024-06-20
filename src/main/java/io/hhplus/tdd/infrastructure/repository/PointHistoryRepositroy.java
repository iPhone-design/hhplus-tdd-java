package io.hhplus.tdd.infrastructure.repository;

import io.hhplus.tdd.domain.entity.PointHistory;

import java.util.List;

public interface PointHistoryRepositroy {
    public List<PointHistory> selectHistory(long userId);
}

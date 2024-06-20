package io.hhplus.tdd.infrastructure.repository;

import io.hhplus.tdd.domain.entity.UserPoint;

public interface UserPointRepository {
    public UserPoint selectPoint(long userId);
    public UserPoint updatePoint(long id, long amount);
}

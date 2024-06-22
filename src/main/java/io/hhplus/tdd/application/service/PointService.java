package io.hhplus.tdd.application.service;

import io.hhplus.tdd.domain.entity.PointHistory;
import io.hhplus.tdd.domain.entity.UserPoint;

import java.util.List;

public interface PointService {
    UserPoint searchPointById(long id);
    List<PointHistory> searchHistoryById(long id);
    UserPoint chargePoint(long id, long amount);
    UserPoint usePoint(long id, long amount);
}

package io.hhplus.tdd.application;

import io.hhplus.tdd.application.service.PointService;
import io.hhplus.tdd.domain.entity.PointHistory;
import io.hhplus.tdd.domain.entity.UserPoint;
import io.hhplus.tdd.infrastructure.PointHistoryRepositoryImpl;
import io.hhplus.tdd.infrastructure.UserPointRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    PointHistoryRepositoryImpl pointHistoryRepositoryImpl;
    @Autowired
    UserPointRepositoryImpl userPointRepositoryImpl;

    /**
     * 특정 유저의 포인트를 조회
     *
     * @author 양종문
     * @since  2024-06-21
     * @param  id
     * @return UserPoint
     */
    @Override
    public UserPoint searchPointById(long id) {
        return userPointRepositoryImpl.selectPoint(id);
    }

    /**
     * 특정 유저의 포인트 충전/이용 내역을 조회
     *
     * @author 양종문
     * @since  2024-06-21
     * @param  userId
     * @return List<PointHistory>
     */
    @Override
    public List<PointHistory> searchHistoryById(long userId) {
        return pointHistoryRepositoryImpl.selectHistory(userId);
    }

    /**
     * 특정 유저의 포인트를 충전
     *
     * @author 양종문
     * @since  2024-06-21
     * @param  id
     * @param  amount
     * @return UserPoint
     */
    @Override
    public UserPoint chargePoint(long id, long amount) {
        return userPointRepositoryImpl.updatePoint(id, amount);
    }

    /**
     * 특정 유저의 포인트를 사용
     *
     * @author 양종문
     * @since  2024-06-21
     * @param  id
     * @param  amount
     * @return UserPoint
     */
    @Override
    public UserPoint usePoint(long id, long amount) {
        return userPointRepositoryImpl.updatePoint(id, amount);
    }
}

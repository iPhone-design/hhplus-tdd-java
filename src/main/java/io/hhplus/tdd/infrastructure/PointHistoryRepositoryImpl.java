package io.hhplus.tdd.infrastructure;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.domain.entity.PointHistory;
import io.hhplus.tdd.infrastructure.repository.PointHistoryRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointHistoryRepositoryImpl implements PointHistoryRepositroy {
    @Autowired
    PointHistoryTable pointHistoryTable;

    @Override
    public List<PointHistory> selectHistory(long userId) {
        return pointHistoryTable.selectAllByUserId(userId);
    }
}

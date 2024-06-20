package io.hhplus.tdd.infrastructure;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.domain.entity.TransactionType;
import io.hhplus.tdd.domain.entity.UserPoint;
import io.hhplus.tdd.infrastructure.repository.UserPointRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserPointRepositoryImpl implements UserPointRepository {
    UserPointTable userPointTable;

    public UserPointRepositoryImpl(UserPointTable userPointTable) {
        this.userPointTable = userPointTable;
    }

    @Override
    public UserPoint selectPoint(long id) {
        return userPointTable.selectById(id);
    }

    @Override
    public UserPoint updatePoint(long id, long amount) {
        return userPointTable.insertOrUpdate(id, amount);
    }
}

package io.hhplus.tdd.application;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.domain.entity.PointHistory;
import io.hhplus.tdd.domain.entity.TransactionType;
import io.hhplus.tdd.domain.entity.UserPoint;
import io.hhplus.tdd.infrastructure.PointHistoryRepositoryImpl;
import io.hhplus.tdd.infrastructure.UserPointRepositoryImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PointServiceImplTest {
    UserPointRepositoryImpl userPointRepositoryImpl = new UserPointRepositoryImpl(new UserPointTable());
    PointHistoryRepositoryImpl pointHistoryRepositoryImpl = new PointHistoryRepositoryImpl(new PointHistoryTable());

    @Test
    @DisplayName("특정 유저의 포인트를 조회")
    void searchPointById() {
        // given
        long id = 10L;                                                              // 유저 ID

        // when
        UserPoint userPoint = userPointRepositoryImpl.selectPoint(id);              // 포인트 조회

        // then
        assertEquals(id, userPoint.id());                                           // 유저 ID 같음
        assertThat(userPoint.point()).isEqualTo(0);                       // 유저 포인트 같음
    }

    @Test
    @DisplayName("특정 유저의 포인트 충전/이용 내역을 조회")
    void searchHistoryById() {
        // given
        long id = 10L;                                                                       // 유저 ID

        // when
        List<PointHistory> listPointHistory = pointHistoryRepositoryImpl.selectHistory(id); // 충전/이용 내역 조회

        // then
        assertThat(listPointHistory.size()).isEqualTo(0);                         // 결과 값이 없는지
    }

    @Test
    @DisplayName("특정 유저의 포인트를 충전")
    void chargePoint() throws Exception {
        // given
        long id = 10L;                                                                                  // 유저 ID
        long amount = 1000L;                                                                            // 기존 포인트
        long chargeAmount = 500L;                                                                       // 충전 포인트

        // when
        if (chargeAmount < 0) {                                                                         // 예외 발생
            throw new Exception("오류 발생");
        }

        UserPoint userPoint = userPointRepositoryImpl.updatePoint(id, amount + chargeAmount); // 포인트 충전

        // then
        assertThat(userPoint.point()).isEqualTo(1500);                                       // 포인트 값 체크
    }

    @Test
    @DisplayName("특정 유저의 포인트를 사용")
    void usePoint() throws Exception {
        // given
        long id = 10L;                                                                              // 유저 ID
        long amount = 1000L;                                                                        // 기존 포인트
        long useAmount = 500L;                                                                      // 사용 포인트

        // when
        if (useAmount > amount) {                                                                   // 예외 발생
            throw new Exception("포인트 부족");
        }
        
        UserPoint userPoint = userPointRepositoryImpl.updatePoint(id, amount - useAmount); // 포인트 사용

        // then
        assertThat(userPoint.point()).isEqualTo(500);                                     // 포인트 값 체크
    }
}
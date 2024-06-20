package io.hhplus.tdd.controller;

import io.hhplus.tdd.application.PointServiceImpl;
import io.hhplus.tdd.application.service.PointService;
import io.hhplus.tdd.domain.entity.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PointController.class)
class PointControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PointServiceImpl pointServiceImpl;

    @Test
    @DisplayName("특정 유저의 포인트를 조회 API")
    void point() throws Exception {
        // given
        long id = 10L;                                                              // 유저 ID
        UserPoint userPoint = UserPoint.empty(id);                                  // 특정 유저

        // when
        when(pointServiceImpl.searchPointById(id)).thenReturn(userPoint);               // 포인트 조회

        // then
        mockMvc.perform(get("/point/{id}", id))                         // "/print/10"로 GET 요청 수행
               .andExpect(status().isOk())                                          // 상태 코드가 200인 성공적인 응답을 기대
               .andExpect(jsonPath("$.id").value(id))                   // JSON 속성 "id"의 값이 userId와 같을 것으로 기대
               .andExpect(jsonPath("$.point").value(0))  // JSON 속성 "point"의 값이 0일 것으로 기대
               .andExpect(jsonPath("$.updateMillis").isNumber());       // JSON 속성 "updateMillis"의 값이 숫자일 것으로 기대
    }

    @Test
    @DisplayName("특정 유저의 포인트 충전/이용 내역을 조회 API")
    void history() throws Exception {
        // given
        long id = 10L;                                                              // 유저 ID

        // when
        when(pointServiceImpl.searchHistoryById(id)).thenReturn(List.of());             // 충전/이용 내역 조회

        // then
        mockMvc.perform(get("/point/{id}/histories", id))               // "/print/10/histories"로 GET 요청 수행
                .andExpect(status().isOk())                                          // 상태 코드가 200인 성공적인 응답을 기대
                .andExpect(jsonPath("$").isArray());                     // 배열 데이터 기대
    }

    @Test
    @DisplayName("특정 유저의 포인트를 충전 API")
    void charge() throws Exception {
        // given
        long id = 10L;                                                                                           // 유저 ID
        long amount = 1000L;                                                                                     // 포인트
        long chargePoint = 500L;                                                                                 // 충전 포인트
        UserPoint userPoint = new UserPoint(id, amount + chargePoint, System.currentTimeMillis());        // 특정 유저

        // when
        when(pointServiceImpl.chargePoint(id, amount)).thenReturn(userPoint);                                       // 포인트 충전

        // then
        mockMvc.perform(patch("/point/{id}/charge", id)
               .contentType(MediaType.APPLICATION_JSON)
               .content(String.valueOf(amount)))                                                                // "/point/10/charge"로 PATCH 요청 수행
               .andExpect(status().isOk())                                                                      // 상태 코드가 200인 성공적인 응답을 기대
               .andExpect(jsonPath("$.id").value(id))                                               // JSON 속성 "id"의 값이 userId와 같을 것으로 기대
               .andExpect(jsonPath("$.point").value(1500))                            // JSON 속성 "point"의 값이 1500일 것으로 기대
               .andExpect(jsonPath("$.updateMillis").isNumber());                                   // JSON 속성 "updateMillis"의 값이 숫자일 것으로 기대
    }

    @Test
    @DisplayName("특정 유저의 포인트를 사용")
    void use() throws Exception {
        // given
        long id = 10L;                                                                                           // 유저 ID
        long amount = 1000L;                                                                                     // 포인트
        long usePoint = 500L;                                                                                   // 사용 포인트
        UserPoint userPoint = new UserPoint(id, amount - usePoint, System.currentTimeMillis());          // 특정 유저

        // when
        when(pointServiceImpl.usePoint(id, amount)).thenReturn(userPoint);                                          // 포인트 사용

        // then
        mockMvc.perform(patch("/point/{id}/use", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(amount)))                                                       // "/point/10/charge"로 PATCH 요청 수행
                .andExpect(status().isOk())                                                                     // 상태 코드가 200인 성공적인 응답을 기대
                .andExpect(jsonPath("$.id").value(id))                                              // JSON 속성 "id"의 값이 userId와 같을 것으로 기대
                .andExpect(jsonPath("$.point").value(500))                           // JSON 속성 "point"의 값이 1500일 것으로 기대
                .andExpect(jsonPath("$.updateMillis").isNumber());                                  // JSON 속성 "updateMillis"의 값이 숫자일 것으로 기대
    }
}
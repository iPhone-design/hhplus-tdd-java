package io.hhplus.tdd.controller;

import io.hhplus.tdd.application.PointServiceImpl;
import io.hhplus.tdd.domain.entity.PointDto;
import io.hhplus.tdd.domain.entity.PointHistory;
import io.hhplus.tdd.domain.entity.UserPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/point")
public class PointController {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    @Autowired
    PointServiceImpl pointServiceImpl;

    /**
     * 특정 유저의 포인트를 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}")
    public PointDto point(@PathVariable long id) {
        return new PointDto("success", pointServiceImpl.searchPointById(id));
    }

    /**
     * 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    public PointDto history(@PathVariable long id) {
        return new PointDto("success", pointServiceImpl.searchHistoryById(id));
    }

    /**
     * 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge")
    public UserPoint charge(@PathVariable long id, @RequestBody long amount) {
        return pointServiceImpl.chargePoint(id, amount);
    }

    /**
     * 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/use")
    public UserPoint use(@PathVariable long id, @RequestBody long amount) {
        return pointServiceImpl.usePoint(id, amount);
    }
}

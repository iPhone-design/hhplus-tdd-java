package io.hhplus.tdd.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PointDto {
    private String result;
    List<Object> list;
    Object object;

    public PointDto(String result, List<Object> list) {
        this.result = result;
        this.list = list;
    }

    public PointDto(String result, Object object) {
        this.result = result;
        this.object = object;
    }

    public PointDto(String result, List<Object> list, Object object) {
        this.result = result;
        this.list = list;
        this.object = object;
    }
}

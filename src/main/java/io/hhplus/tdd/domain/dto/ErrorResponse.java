package io.hhplus.tdd.domain.dto;

public record ErrorResponse(
        String code,
        String message
) {
}

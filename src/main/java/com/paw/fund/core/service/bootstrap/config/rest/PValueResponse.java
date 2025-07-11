package com.paw.fund.core.service.bootstrap.config.rest;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record PValueResponse<T> (
        T data,
        String status,
        Boolean success,
        String errorCode,
        LocalDateTime responseAt,
        String message
) {
    public static <T> PValueResponse<T> success(T data) {
        return PValueResponse.<T>builder()
                .data(data)
                .status(String.valueOf(HttpStatus.OK.value()))
                .success(true)
                .message("Dịch vụ đã được thực hiện")
                .responseAt(LocalDateTime.now())
                .build();
    }

    public static <T> PValueResponse<T> successNoData() {
        return PValueResponse.<T>builder()
                .data(null)
                .status(String.valueOf(HttpStatus.OK.value()))
                .success(true)
                .message("Dịch vụ đã được thực hiện")
                .responseAt(LocalDateTime.now())
                .build();
    }

    public static <T> PValueResponse error(
            String status,
            String errorCode,
            String message
    ) {
        return PValueResponse.builder()
                .status(status)
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .responseAt(LocalDateTime.now())
                .build();
    }
}

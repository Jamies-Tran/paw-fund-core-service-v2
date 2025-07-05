package com.paw.fund.core.service.features.login.info.controller.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LoginInfoRequest(
        @NotNull(
                message = "Vui lập nhập email"
        )
        @Pattern(
                message = "Email không hợp lệ",
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )
        @Schema(
                example = "nguyenvanbe@gmail.com"
        )
        String email,

        @NotNull(
                message = "Vui lập nhập password"
        )
        @Schema(
                example = "nguyenvanbe"
        )
        String password,

        @Schema(
                example = "0.0"
        )
        BigDecimal latitude,

        @Schema(
                example = "0.0"
        )
        BigDecimal longitude
) {
}

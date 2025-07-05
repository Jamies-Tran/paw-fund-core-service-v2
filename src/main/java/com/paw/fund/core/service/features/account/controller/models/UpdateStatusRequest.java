package com.paw.fund.core.service.features.account.controller.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateStatusRequest(
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
                message = "Vui lập nhập mã xác nhận"
        )
        String verificationCode
) {
}

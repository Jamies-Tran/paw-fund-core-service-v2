package com.paw.fund.core.service.features.account.controller.models;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequest(
        @NotNull(
                message = "Mã xác nhận không được bỏ trống"
        )
        String verificationCode,
        @NotNull(
                message = "Mật khẩu mới không được bỏ trống"
        )
        String newPassword
) {
}

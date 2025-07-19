package com.paw.fund.core.service.features.account.controller.models;

import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(
        @NotNull(
                message = "Vui lập nhập mã xác nhận"
        )
        String verificationCode
) {
}

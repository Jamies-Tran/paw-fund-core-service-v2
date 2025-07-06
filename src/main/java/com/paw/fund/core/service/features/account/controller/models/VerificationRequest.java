package com.paw.fund.core.service.features.account.controller.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record VerificationRequest(
        @NotNull(
                message = "Vui lập nhập mã xác nhận"
        )
        String verification
) {
}

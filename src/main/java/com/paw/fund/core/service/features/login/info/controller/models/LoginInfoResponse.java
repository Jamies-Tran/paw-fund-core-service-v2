package com.paw.fund.core.service.features.login.info.controller.models;

import com.paw.fund.core.service.features.login.info.controller.models.login.account.LoginAccountResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LoginInfoResponse(
        Long loginInfoId,
        String accessToken,
        String refreshToken,
        LocalDateTime accessExpiredAt,
        LocalDateTime refreshExpiredAt,
        BigDecimal latitude,
        BigDecimal longitude,
        LoginAccountResponse account
) {
}

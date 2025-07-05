package com.paw.fund.core.service.domain.login.info;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record LoginInfo(
        Long loginInfoId,
        @With String accessToken,
        String refreshToken,
        Long accountId,
        String accountEmail,
        LocalDateTime accessExpiredAt,
        LocalDateTime refreshExpiredAt,
        BigDecimal latitude,
        BigDecimal longitude,
        String statusCode,
        String statusName,
        @With LoginAccount account
) {
}

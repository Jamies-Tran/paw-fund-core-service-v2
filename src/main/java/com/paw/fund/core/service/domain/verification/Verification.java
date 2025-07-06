package com.paw.fund.core.service.domain.verification;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Verification(
        Long verificationCodeId,
        Long accountId,
        String dataHolder,
        String code,
        String typeCode,
        String typeName,
        LocalDateTime expiredAt
) {
}

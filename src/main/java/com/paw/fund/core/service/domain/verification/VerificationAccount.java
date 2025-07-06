package com.paw.fund.core.service.domain.verification;

import lombok.Builder;

@Builder
public record VerificationAccount(
        Long accountId,
        String email
) {
}

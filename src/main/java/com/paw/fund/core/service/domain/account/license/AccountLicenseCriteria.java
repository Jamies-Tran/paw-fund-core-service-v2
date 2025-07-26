package com.paw.fund.core.service.domain.account.license;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountLicenseCriteria(
        List<LocalDateTime> timeRange,
        @With Long accountId
) {
    public AccountLicenseCriteria {
        timeRange = PObjectUtils.defaultTimeRange(timeRange);
    }

    public static AccountLicenseCriteria of(List<LocalDateTime> timeRange, Long accountId) {
        return AccountLicenseCriteria.builder()
                .timeRange(timeRange)
                .accountId(accountId)
                .build();
    }

    public Boolean isAccountIdNull() {
        return PObjectUtils.isNull(accountId);
    }
}

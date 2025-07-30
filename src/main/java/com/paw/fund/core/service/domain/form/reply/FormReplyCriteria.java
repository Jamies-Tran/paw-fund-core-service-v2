package com.paw.fund.core.service.domain.form.reply;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FormReplyCriteria(
        String search,
        @With Long accountId,
        List<LocalDateTime> timeRange,
        List<String> statusCodes
) {
    public FormReplyCriteria {
        search = PObjectUtils.defaultString(search);
        timeRange = PObjectUtils.defaultTimeRange(timeRange);
        statusCodes = PObjectUtils.defaultList(statusCodes);
    }

    public static FormReplyCriteria of(
            String search,
            Long accountId,
            List<LocalDateTime> timeRange,
            List<String> statusCodes
    ) {
        return FormReplyCriteria.builder()
                .search(search)
                .accountId(accountId)
                .timeRange(timeRange)
                .statusCodes(statusCodes)
                .build();
    }

    public Boolean isAccountIdNull() {
        return PObjectUtils.isNull(accountId);
    }
}

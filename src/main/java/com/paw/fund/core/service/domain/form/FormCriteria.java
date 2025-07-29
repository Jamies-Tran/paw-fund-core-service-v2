package com.paw.fund.core.service.domain.form;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FormCriteria(
        String search,
        List<LocalDateTime> timeRange,
        List<String> formTypeCodes,
        @With List<String> statusCodes
) {
    public FormCriteria {
        search = PObjectUtils.defaultString(search);
        timeRange = PObjectUtils.defaultTimeRange(timeRange);
        formTypeCodes = PObjectUtils.defaultList(formTypeCodes);
        statusCodes = PObjectUtils.defaultList(statusCodes);
    }

    public static FormCriteria of(
            String search,
            List<LocalDateTime> timeRange,
            List<String> formTypeCodes,
            List<String> statusCodes
    ) {
        return FormCriteria.builder()
                .search(search)
                .timeRange(timeRange)
                .formTypeCodes(formTypeCodes)
                .statusCodes(statusCodes)
                .build();
    }
}

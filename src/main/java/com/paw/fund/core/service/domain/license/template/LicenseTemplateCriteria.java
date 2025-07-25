package com.paw.fund.core.service.domain.license.template;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LicenseTemplateCriteria(
        String search,
        List<LocalDateTime> timeRange,
        @With List<String> statusCodes,
        List<String> licenseTypeCodes
) {
    public LicenseTemplateCriteria {
        search = PObjectUtils.defaultString(search);
        timeRange = PObjectUtils.defaultTimeRange(timeRange);
        statusCodes = PObjectUtils.defaultList(statusCodes);
        licenseTypeCodes = PObjectUtils.defaultList(licenseTypeCodes);
    }

    public static LicenseTemplateCriteria of(
            String search,
            List<LocalDateTime> timeRange,
            List<String> statusCodes,
            List<String> licenseTypeCodes
    ) {
        return LicenseTemplateCriteria.builder()
                .search(search)
                .timeRange(timeRange)
                .statusCodes(statusCodes)
                .licenseTypeCodes(licenseTypeCodes)
                .build();
    }
}

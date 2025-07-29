package com.paw.fund.core.service.domain.account.license.content;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.enums.EEnableStatus;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record AccountLicenseContent(
        Long accountLicenseContentId,
        @With Long accountLicenseId,
        Long templateSectionContentId,
        LicenseContent content,
        String statusCode,
        String statusName
) {
    public record LicenseContent(
            String sectionContent,
            String licenseTemplateContent,
            String licenseContent
    ) {
    }

    public AccountLicenseContent {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

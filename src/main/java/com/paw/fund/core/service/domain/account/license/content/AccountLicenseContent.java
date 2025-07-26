package com.paw.fund.core.service.domain.account.license.content;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.enums.EEnableStatus;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.With;

@Builder
public record AccountLicenseContent(
        Long accountLicenseContentId,
        @With Long accountLicenseId,
        Long templateSectionContentId,
        String content,
        String statusCode,
        String statusName
) {
    public AccountLicenseContent {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

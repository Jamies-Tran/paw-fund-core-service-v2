package com.paw.fund.core.service.domain.account.license;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.domain.media.info.MediaInfoList;
import com.paw.fund.core.service.enums.account.license.EAccountLicenseStatus;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountLicense (
        Long accountLicenseId,
        Long licenseTemplateId,
        @With Long accountId,
        String licenseNumber,
        LocalDateTime expiryDate,
        LocalDateTime issueDate,
        String statusCode,
        String statusName,
        MediaInfoList media,
        @With List<AccountLicenseContent> contents
) {
    public AccountLicense {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EAccountLicenseStatus.ENABLE.getCode();
            statusName = EAccountLicenseStatus.ENABLE.getName();
        }
    }
}

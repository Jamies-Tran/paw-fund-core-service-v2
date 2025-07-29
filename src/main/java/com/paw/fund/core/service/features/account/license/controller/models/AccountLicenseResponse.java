package com.paw.fund.core.service.features.account.license.controller.models;

import com.paw.fund.core.service.domain.media.info.MediaInfoList;
import com.paw.fund.core.service.features.account.license.controller.models.content.AccountLicenseContentResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountLicenseResponse(
        Long accountLicenseId,
        Long licenseTemplateId,
        Long accountId,
        String licenseNumber,
        LocalDateTime expiryDate,
        LocalDateTime issueDate,
        MediaInfoList media,
        String statusCode,
        String statusName,
        List<AccountLicenseContentResponse> contents
) {
}

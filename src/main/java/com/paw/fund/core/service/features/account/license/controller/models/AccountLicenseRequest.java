package com.paw.fund.core.service.features.account.license.controller.models;

import com.paw.fund.core.service.domain.media.info.MediaInfoRequest;
import com.paw.fund.core.service.features.account.license.controller.models.content.AccountLicenseContentRequest;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountLicenseRequest(
        Long licenseTemplateId,
        String licenseNumber,
        LocalDateTime expiryDate,
        LocalDateTime issueDate,
        MediaInfoRequest media,
        List<AccountLicenseContentRequest> contents
) {
}

package com.paw.fund.core.service.features.account.license.controller.models;

import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.features.account.license.controller.models.content.AccountLicenseContentRequest;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountLicenseRequest(
        Long licenseTemplateId,
        String licenseNumber,
        LocalDateTime expiryDate,
        LocalDateTime issueDate,
        List<AccountLicenseContentRequest> contents
) {
}

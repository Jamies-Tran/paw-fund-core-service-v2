package com.paw.fund.core.service.features.account.license.controller.models.content;

import lombok.Builder;

@Builder
public record AccountLicenseContentResponse(
        Long accountLicenseContentId,
        Long accountLicenseId,
        Long templateSectionContentId,
        String sectionContent,
        LicenseContentResponse content
) {
    public record LicenseContentResponse(
            String sectionContent,
            String licenseTemplateContent,
            String licenseContent
    ) {
    }
}

package com.paw.fund.core.service.features.account.license.controller.models;

import lombok.Builder;

import java.util.List;

@Builder
public record AccountLicenseDetailResponse(
        Long accountLicenseId,
        Long licenseTemplateId,
        String title,
        String description,
        List<LicenseSectionDetailResponse> sections
) {
    public record LicenseSectionDetailResponse(
            Long licenseTemplateSectionId,
            String sectionTitle,
            List<SectionContentDetailResponse> contents
    ) {
        public record SectionContentDetailResponse(
                Long accountLicenseContentId,
                Long templateSectionContentId,
                String sectionContent,
                String licenseContent
        ) {}
    }
}

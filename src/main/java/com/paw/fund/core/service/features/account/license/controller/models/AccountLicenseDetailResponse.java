package com.paw.fund.core.service.features.account.license.controller.models;

import com.paw.fund.core.service.domain.account.license.AccountLicenseDetail;
import com.paw.fund.core.service.domain.media.info.MediaInfoList;
import lombok.Builder;

import java.util.List;

@Builder
public record AccountLicenseDetailResponse(
        Long accountLicenseId,
        Long licenseTemplateId,
        String title,
        String description,
        MediaInfoList media,
        List<SectionContentDetailResponse> licenseContent
) {
    public record SectionContentDetailResponse(
            String sectionContent,
            List<ContentDetailResponse> contents
    ) {
        public record ContentDetailResponse(
                Long accountLicenseContentId,
                String licenseTemplateContent,
                String licenseContent
        ) {

        }
    }
}

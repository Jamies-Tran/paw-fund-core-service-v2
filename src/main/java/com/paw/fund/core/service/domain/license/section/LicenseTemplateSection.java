package com.paw.fund.core.service.domain.license.section;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.enums.EEnableStatus;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record LicenseTemplateSection(
        Long licenseTemplateSectionId,
        @With Long licenseTemplateId,
        String sectionTitle,
        String statusCode,
        String statusName,
        @With List<TemplateSectionContent> contents
) {
    public LicenseTemplateSection {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

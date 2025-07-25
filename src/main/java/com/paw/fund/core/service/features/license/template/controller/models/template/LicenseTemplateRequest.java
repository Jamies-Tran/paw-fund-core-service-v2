package com.paw.fund.core.service.features.license.template.controller.models.template;

import com.paw.fund.core.service.features.license.template.controller.models.section.LicenseTemplateSectionRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseTemplateRequest(
        String title,
        String description,

        String licenseTypeCode,
        String licenseTypeName,

        List<LicenseTemplateSectionRequest> sections
) {
}

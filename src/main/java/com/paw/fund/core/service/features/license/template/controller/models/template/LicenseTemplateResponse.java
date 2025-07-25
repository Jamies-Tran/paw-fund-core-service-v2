package com.paw.fund.core.service.features.license.template.controller.models.template;

import com.paw.fund.core.service.features.license.template.controller.models.section.LicenseTemplateSectionResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseTemplateResponse(
        Long licenseTemplateId,

        String title,
        String description,


        String licenseTypeCode,
        String licenseTypeName,


        String statusCode,
        String statusName,

        List<LicenseTemplateSectionResponse> sections
) {
}

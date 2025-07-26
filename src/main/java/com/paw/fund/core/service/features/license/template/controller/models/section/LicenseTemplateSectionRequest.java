package com.paw.fund.core.service.features.license.template.controller.models.section;

import com.paw.fund.core.service.features.license.template.controller.models.section.content.TemplateSectionContentRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseTemplateSectionRequest(
        Long licenseTemplateSectionId,
        String sectionTitle,
        List<TemplateSectionContentRequest> contents
) {
}

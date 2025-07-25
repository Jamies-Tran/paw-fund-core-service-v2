package com.paw.fund.core.service.features.license.template.controller.models.section;

import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.features.license.template.controller.models.section.content.TemplateSectionContentResponse;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record LicenseTemplateSectionResponse(
        Long licenseTemplateSectionId,
        Long licenseTemplateId,
        String sectionTitle,
        List<TemplateSectionContentResponse> contents
) {
}

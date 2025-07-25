package com.paw.fund.core.service.features.license.template.controller.models.section.content;

import lombok.Builder;

@Builder
public record TemplateSectionContentResponse(
        Long templateSectionContentId,
        Long licenseTemplateSectionId,
        String content
) {
}

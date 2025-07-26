package com.paw.fund.core.service.features.license.template.controller.models.section.content;

import lombok.Builder;

@Builder
public record TemplateSectionContentRequest(
        Long templateSectionContentId,
        String content
) {
}

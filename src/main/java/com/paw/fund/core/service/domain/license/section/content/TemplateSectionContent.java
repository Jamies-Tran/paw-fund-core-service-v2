package com.paw.fund.core.service.domain.license.section.content;

import lombok.Builder;
import lombok.With;

@Builder
public record TemplateSectionContent(
        Long templateSectionContentId,
        @With Long licenseTemplateSectionId,
        String content
) {
}

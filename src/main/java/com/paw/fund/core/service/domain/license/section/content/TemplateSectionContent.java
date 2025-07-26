package com.paw.fund.core.service.domain.license.section.content;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.enums.EEnableStatus;
import lombok.Builder;
import lombok.With;

@Builder
public record TemplateSectionContent(
        Long templateSectionContentId,
        @With Long licenseTemplateSectionId,
        String content,
        String statusCode,
        String statusName
) {
    public TemplateSectionContent {
        if (PObjectUtils.isNull(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

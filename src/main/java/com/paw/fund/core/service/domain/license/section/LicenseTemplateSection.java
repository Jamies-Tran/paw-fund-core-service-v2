package com.paw.fund.core.service.domain.license.section;

import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record LicenseTemplateSection(
        Long licenseTemplateSectionId,
        @With Long licenseTemplateId,
        String sectionTitle,
        @With List<TemplateSectionContent> contents
) {
}

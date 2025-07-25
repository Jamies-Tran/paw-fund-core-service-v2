package com.paw.fund.core.service.domain.license.section;

import com.paw.fund.core.service.domain.license.section.content.ITemplateSectionContentUseCase;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class LicenseTemplateSectionPrivateService {
    @Autowired
    ITemplateSectionContentUseCase sectionContentUseCase;

    protected Map<Long, List<TemplateSectionContent>> templateSectionContents(List<Long> licenseTemplateSectionIds) {
        return sectionContentUseCase.findAllByLicenseTemplateSectionIdIn(licenseTemplateSectionIds)
                .stream()
                .collect(Collectors.groupingBy(TemplateSectionContent::licenseTemplateSectionId));
    }
}

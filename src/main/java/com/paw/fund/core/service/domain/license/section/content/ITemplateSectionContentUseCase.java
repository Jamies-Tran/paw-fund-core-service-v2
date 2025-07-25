package com.paw.fund.core.service.domain.license.section.content;

import java.util.List;

public interface ITemplateSectionContentUseCase {
    void save(Long licenseTemplateSectionId, List<TemplateSectionContent> contents);

    List<TemplateSectionContent> findAllByLicenseTemplateSectionIdIn(List<Long> licenseTemplateSectionIds);

    void deleteAllByLicenseTemplateSectionIdIn(List<Long> licenseTemplateSectionIds);
}

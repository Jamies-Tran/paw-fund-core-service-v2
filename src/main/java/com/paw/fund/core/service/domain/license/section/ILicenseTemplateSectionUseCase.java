package com.paw.fund.core.service.domain.license.section;

import java.util.List;

public interface ILicenseTemplateSectionUseCase {
    void save(Long licenseTemplateId, List<LicenseTemplateSection> sections);

    List<LicenseTemplateSection> findAllByLicenseTemplateId(Long licenseTemplateId);

    void update(Long licenseTemplateId, List<LicenseTemplateSection> sections);
}

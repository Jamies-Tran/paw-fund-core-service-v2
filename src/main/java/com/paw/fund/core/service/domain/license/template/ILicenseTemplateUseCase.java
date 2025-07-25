package com.paw.fund.core.service.domain.license.template;

import com.paw.fund.core.service.enums.license.template.ELicenseTemplateStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ILicenseTemplateUseCase {
    Long save(LicenseTemplate licenseTemplate);

    Optional<LicenseTemplate> findById(Long licenseTemplateId);

    void update(Long licenseTemplateId, LicenseTemplate licenseTemplate);

    void enable(Long licenseTemplateId);

    void disable(Long licenseTemplateId);

    void delete(Long licenseTemplateId);

    Page<LicenseTemplate> findAll(LicenseTemplateCriteria criteria, PageRequest pageRequest);
}

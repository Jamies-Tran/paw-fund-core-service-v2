package com.paw.fund.core.service.features.license.template.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.license.template.ILicenseTemplateUseCase;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.domain.license.template.LicenseTemplateCriteria;
import com.paw.fund.core.service.domain.license.template.LicenseTemplatePrivateService;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
import com.paw.fund.core.service.enums.license.template.ELicenseTemplateStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateUseCaseService extends LicenseTemplatePrivateService
        implements ILicenseTemplateUseCase {
    LicenseTemplateCommandService commandService;

    LicenseTemplateQueryService queryService;

    @Override
    @Transactional
    public Long save(LicenseTemplate licenseTemplate) {
        Long licenseTemplateId = commandService.save(licenseTemplate);
        saveSections(licenseTemplateId, licenseTemplate.sections());

        return licenseTemplateId;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LicenseTemplate> findById(Long licenseTemplateId) {
        return queryService.findById(licenseTemplateId)
                .map(licenseTemplate -> licenseTemplate
                        .withSections(licenseTemplateSections(licenseTemplate.licenseTemplateId())));
    }

    @Override
    @Transactional
    public void update(Long licenseTemplateId, LicenseTemplate licenseTemplate) {
        updateSections(licenseTemplateId, licenseTemplate);
        commandService.update(licenseTemplateId, licenseTemplate);
    }

    @Override
    @Transactional
    public void enable(Long licenseTemplateId) {
        commandService.updateStatus(licenseTemplateId, ELicenseTemplateStatus.ENABLED);
    }

    @Override
    @Transactional
    public void disable(Long licenseTemplateId) {
        commandService.updateStatus(licenseTemplateId, ELicenseTemplateStatus.DISABLED);
    }


    @Override
    @Transactional
    public void delete(Long licenseTemplateId) {
        commandService.delete(licenseTemplateId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LicenseTemplate> findAll(LicenseTemplateCriteria criteria, PageRequest pageRequest) {
        List<Role> roles = loginAccount().roles();
        if (PObjectUtils.isNotEmptyList(roles)
                && roles.stream().anyMatch(r -> PObjectUtils.isNotEqual(ERole.ADMIN.getCode(), r.roleCode()))) {
            criteria = criteria.withStatusCodes(List.of(ELicenseTemplateStatus.ENABLED.getCode()));
        }

        return queryService.findAll(criteria, pageRequest);
    }
}

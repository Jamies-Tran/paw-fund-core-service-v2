package com.paw.fund.core.service.features.license.template.service;

import com.paw.fund.core.service.bootstrap.utils.PSpringContext;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.domain.license.template.LicenseTemplateCriteria;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.features.license.template.repository.database.ILicenseTemplateMapper;
import com.paw.fund.core.service.features.license.template.repository.database.ILicenseTemplateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateQueryService {
    ILicenseTemplateRepository repository;

    ILicenseTemplateMapper mapper;

    protected Optional<LicenseTemplate> findById(Long licenseTemplateId) {
        return repository.findById(licenseTemplateId)
                .map(mapper::toDto);
    }

    protected Page<LicenseTemplate> findAll(LicenseTemplateCriteria searchCriteria, PageRequest pageRequest) {

        return repository.findAll(searchCriteria, pageRequest)
                .map(mapper::toDto);
    }
}

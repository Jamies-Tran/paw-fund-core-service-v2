package com.paw.fund.core.service.features.license.section.service;

import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionMapper;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateSectionQueryService {
    ILicenseTemplateSectionRepository repository;

    ILicenseTemplateSectionMapper mapper;

    protected List<LicenseTemplateSection> findAllByLicenseTemplateId(Long licenseTemplateId) {
        return mapper.toDto(repository.findAllByLicenseTemplateId(licenseTemplateId));
    }
}

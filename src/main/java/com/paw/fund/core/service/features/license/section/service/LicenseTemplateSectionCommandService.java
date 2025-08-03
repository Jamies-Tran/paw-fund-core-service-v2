package com.paw.fund.core.service.features.license.section.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.bootstrap.utils.PSpringContext;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.section.content.ITemplateSectionContentUseCase;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionMapper;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionRepository;
import com.paw.fund.core.service.features.license.section.repository.database.LicenseTemplateSectionEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateSectionCommandService {
    ILicenseTemplateSectionRepository repository;

    ILicenseTemplateSectionMapper mapper;

    @Lazy
    ITemplateSectionContentUseCase sectionContentUseCase;

    protected void save(Long licenseTemplateId, List<LicenseTemplateSection> sections) {
        sections.forEach(section -> {
                    LicenseTemplateSectionEntity section1 = repository.save(mapper.toEntity(section.withLicenseTemplateId(licenseTemplateId)));
                    sectionContentUseCase.save(section1.getLicenseTemplateSectionId(), section.contents());
                });
    }

    protected void update(Long licenseTemplateId, List<LicenseTemplateSection> sections) {
        List<LicenseTemplateSectionEntity> oldSections = repository
                .findAllByLicenseTemplateId(licenseTemplateId);
        sectionContentUseCase.deleteByLicenseTemplateSectionId(oldSections.stream()
                .map(LicenseTemplateSectionEntity::getLicenseTemplateSectionId).toList());
        repository.deleteAll(oldSections);

        Map<Long, List<TemplateSectionContent>> sectionContents = new HashMap<>();

        sections.forEach(section -> {
            LicenseTemplateSectionEntity newSection = repository.save(mapper
                    .toEntity(section.withLicenseTemplateId(licenseTemplateId)));
            sectionContents.put(newSection.getLicenseTemplateSectionId(), section.contents());
        });

        sectionContents.forEach(sectionContentUseCase::update);
    }
}

package com.paw.fund.core.service.features.license.section.service;

import com.paw.fund.core.service.bootstrap.utils.PSpringContext;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.section.content.ITemplateSectionContentUseCase;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionMapper;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionRepository;
import com.paw.fund.core.service.features.license.section.repository.database.LicenseTemplateSectionEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateSectionCommandService {
    ILicenseTemplateSectionRepository repository;

    ILicenseTemplateSectionMapper mapper;

    protected void save(Long licenseTemplateId, List<LicenseTemplateSection> sections) {
        ITemplateSectionContentUseCase sectionContentUseCase = PSpringContext
                .getBean(ITemplateSectionContentUseCase.class);

        sections.forEach(section -> {
                    LicenseTemplateSectionEntity section1 = repository.save(mapper.toEntity(section.withLicenseTemplateId(licenseTemplateId)));
                    sectionContentUseCase.save(section1.getLicenseTemplateSectionId(), section.contents());
                });
    }

    protected void update(Long licenseTemplateId, List<LicenseTemplateSection> sections) {
        ITemplateSectionContentUseCase contentUseCase = PSpringContext
                .getBean(ITemplateSectionContentUseCase.class);
        List<Long> oldSectionIds = repository
                .findAllByLicenseTemplateId(licenseTemplateId)
                .stream()
                .map(LicenseTemplateSectionEntity::getLicenseTemplateSectionId)
                .toList();
        contentUseCase.deleteAllByLicenseTemplateSectionIdIn(oldSectionIds);
        repository.deleteAllByLicenseTemplateId(licenseTemplateId);

        sections.forEach(section -> {
            LicenseTemplateSectionEntity section1 = repository
                    .save(mapper.toEntity(section.withLicenseTemplateId(licenseTemplateId)));
            contentUseCase.save(section1.getLicenseTemplateSectionId(), section.contents());
        });

    }
}

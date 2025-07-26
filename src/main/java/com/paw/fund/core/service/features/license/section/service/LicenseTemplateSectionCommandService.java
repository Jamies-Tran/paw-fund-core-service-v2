package com.paw.fund.core.service.features.license.section.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.bootstrap.utils.PSpringContext;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.section.content.ITemplateSectionContentUseCase;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionMapper;
import com.paw.fund.core.service.features.license.section.repository.database.ILicenseTemplateSectionRepository;
import com.paw.fund.core.service.features.license.section.repository.database.LicenseTemplateSectionEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        ITemplateSectionContentUseCase contentUseCase = PSpringContext.getBean(ITemplateSectionContentUseCase.class);
        Map<Long, LicenseTemplateSection> sectionMap = sections.stream()
                .filter(section -> PObjectUtils.isNotNull(section.licenseTemplateSectionId()))
                .collect(Collectors.toMap(LicenseTemplateSection::licenseTemplateSectionId, section -> section));

        repository.findAllByLicenseTemplateId(licenseTemplateId)
                .forEach(foundSection -> {
                    if (sectionMap.containsKey(foundSection.getLicenseTemplateSectionId())) {
                        LicenseTemplateSection section = sectionMap.get(foundSection.getLicenseTemplateSectionId());
                        mapper.update(foundSection, section);
                        contentUseCase.update(foundSection.getLicenseTemplateSectionId(), section.contents());
                        repository.save(foundSection);
                    } else {
                        foundSection.setStatusCode(EDeleteStatus.DELETED.getCode());
                        foundSection.setStatusName(EDeleteStatus.DELETED.getName());
                        repository.save(foundSection);
                    }
                });

        sections.stream()
                .filter(section -> PObjectUtils.isNull(section.licenseTemplateSectionId()))
                .forEach(section -> {
                    LicenseTemplateSectionEntity section1 = repository.save(mapper
                            .toEntity(section.withLicenseTemplateId(licenseTemplateId)));
                    contentUseCase.save(section1.getLicenseTemplateSectionId(), section.contents());
                });
    }
}

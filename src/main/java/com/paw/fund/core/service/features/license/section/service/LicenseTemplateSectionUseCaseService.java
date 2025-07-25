package com.paw.fund.core.service.features.license.section.service;

import com.paw.fund.core.service.domain.license.section.ILicenseTemplateSectionUseCase;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSectionPrivateService;
import com.paw.fund.core.service.domain.license.section.content.ITemplateSectionContentUseCase;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateSectionUseCaseService extends LicenseTemplateSectionPrivateService
        implements ILicenseTemplateSectionUseCase {
    LicenseTemplateSectionCommandService commandService;

    LicenseTemplateSectionQueryService queryService;


    @Override
    @Transactional
    public void save(Long licenseTemplateId, List<LicenseTemplateSection> sections) {
        commandService.save(licenseTemplateId, sections);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenseTemplateSection> findAllByLicenseTemplateId(Long licenseTemplateId) {
        List<LicenseTemplateSection> sections = queryService.findAllByLicenseTemplateId(licenseTemplateId);
        Map<Long, List<TemplateSectionContent>> templateSectionContents = templateSectionContents(sections
                .stream()
                .map(LicenseTemplateSection::licenseTemplateSectionId)
                .toList());

        return sections.stream()
                .map(section -> section
                        .withContents(templateSectionContents.computeIfAbsent(section.licenseTemplateSectionId(), s -> List.of())))
                .toList();
    }

    @Override
    @Transactional
    public void update(Long licenseTemplateId, List<LicenseTemplateSection> sections) {
        commandService.update(licenseTemplateId, sections);
    }
}

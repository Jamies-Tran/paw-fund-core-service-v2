package com.paw.fund.core.service.features.license.section.content.service;

import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.features.license.section.content.repository.database.ITemplateSectionContentMapper;
import com.paw.fund.core.service.features.license.section.content.repository.database.ITemplateSectionContentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemplateSectionContentCommandService {
    ITemplateSectionContentRepository repository;

    ITemplateSectionContentMapper mapper;

    protected void save(Long licenseTemplateSectionId, List<TemplateSectionContent> contents) {
        repository.saveAll(mapper.toEntity(contents.stream()
                        .map(section -> section
                                .withLicenseTemplateSectionId(licenseTemplateSectionId))
                        .toList()));
    }

    protected void update(Long licenseTemplateSectionId, List<TemplateSectionContent> templateSectionContents) {
        repository.deleteAll(repository.findAllByLicenseTemplateSectionId(licenseTemplateSectionId));

        repository.saveAll(mapper.toEntity(templateSectionContents.stream()
                .map(content -> content
                        .withLicenseTemplateSectionId(licenseTemplateSectionId)).toList()));
    }

    protected void deleteByLicenseTemplateSectionId(List<Long> licenseTemplateSectionIds) {
        repository.deleteAllByLicenseTemplateSectionIdIn(licenseTemplateSectionIds);
    }
}

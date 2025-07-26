package com.paw.fund.core.service.features.license.section.content.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.license.section.content.repository.database.ITemplateSectionContentMapper;
import com.paw.fund.core.service.features.license.section.content.repository.database.ITemplateSectionContentRepository;
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
        Map<Long, TemplateSectionContent> contents = templateSectionContents.stream()
                .filter(content -> PObjectUtils.isNotNull(content.templateSectionContentId()))
                .collect(Collectors.toMap(TemplateSectionContent::templateSectionContentId, content -> content));
        repository.findAllByLicenseTemplateSectionId(licenseTemplateSectionId).forEach(foundContent -> {
            if (contents.containsKey(foundContent.getTemplateSectionContentId())) {
                mapper.update(foundContent, contents.get(foundContent.getTemplateSectionContentId()));
                repository.save(foundContent);
            } else {
                foundContent.setStatusName(EDeleteStatus.DELETED.getName());
                foundContent.setStatusCode(EDeleteStatus.DELETED.getCode());
                repository.save(foundContent);
            }
        });
        repository.saveAll(mapper
                .toEntity(templateSectionContents.stream()
                        .filter(content -> PObjectUtils.isNull(content.templateSectionContentId()))
                        .map(content -> content.withLicenseTemplateSectionId(licenseTemplateSectionId))
                        .toList()));
    }
}

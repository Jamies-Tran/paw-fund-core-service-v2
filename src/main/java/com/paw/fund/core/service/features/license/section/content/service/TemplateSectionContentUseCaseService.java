package com.paw.fund.core.service.features.license.section.content.service;

import com.paw.fund.core.service.domain.license.section.content.ITemplateSectionContentUseCase;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemplateSectionContentUseCaseService implements ITemplateSectionContentUseCase {
    TemplateSectionContentCommandService commandService;

    TemplateSectionContentQueryService queryService;

    @Override
    @Transactional
    public void save(Long licenseTemplateSectionId, List<TemplateSectionContent> contents) {
        commandService.save(licenseTemplateSectionId, contents);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TemplateSectionContent> findAllByLicenseTemplateSectionIdIn(List<Long> licenseTemplateSectionIds) {
        return queryService.findAllByLicenseTemplateSectionIdIn(licenseTemplateSectionIds);
    }

    @Override
    @Transactional
    public void deleteAllByLicenseTemplateSectionIdIn(List<Long> licenseTemplateSectionIds) {
        commandService.deleteAllByLicenseTemplateSectionIdIn(licenseTemplateSectionIds);
    }
}

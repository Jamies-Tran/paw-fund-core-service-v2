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
public class TemplateSectionContentQueryService {
    ITemplateSectionContentRepository repository;

    ITemplateSectionContentMapper mapper;

    protected List<TemplateSectionContent> findAllByLicenseTemplateSectionIdIn(List<Long> ids) {
        return mapper.toDto(repository.findAllByLicenseTemplateSectionIdIn(ids));
    }
}

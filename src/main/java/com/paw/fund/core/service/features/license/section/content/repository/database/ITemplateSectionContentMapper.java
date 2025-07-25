package com.paw.fund.core.service.features.license.section.content.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ITemplateSectionContentMapper extends IEntityMapper<TemplateSectionContentEntity, TemplateSectionContent> {
}

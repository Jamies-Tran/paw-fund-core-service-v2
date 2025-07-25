package com.paw.fund.core.service.features.license.section.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ILicenseTemplateSectionMapper extends IEntityMapper<LicenseTemplateSectionEntity, LicenseTemplateSection> {
}

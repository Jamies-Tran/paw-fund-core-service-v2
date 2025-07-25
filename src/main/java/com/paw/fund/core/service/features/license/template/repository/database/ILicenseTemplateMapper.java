package com.paw.fund.core.service.features.license.template.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ILicenseTemplateMapper extends IEntityMapper<LicenseTemplateEntity, LicenseTemplate> {
    void update(@MappingTarget LicenseTemplateEntity entity, LicenseTemplate dto);
}

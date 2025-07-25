package com.paw.fund.core.service.features.license.template.controller.models.mappers.section;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.section.content.ITemplateSectionContentResponseMapper;
import com.paw.fund.core.service.features.license.template.controller.models.section.LicenseTemplateSectionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ITemplateSectionContentResponseMapper.class}
)
public interface ILicenseTemplateSectionResponseMapper extends IModelMapper<LicenseTemplateSectionResponse, LicenseTemplateSection> {
}

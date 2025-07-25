package com.paw.fund.core.service.features.license.template.controller.models.mappers.template;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateRequest;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.section.ILicenseTemplateSectionRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ILicenseTemplateSectionRequestMapper.class}
)
public interface ILicenseTemplateRequestMapper extends IModelMapper<LicenseTemplateRequest, LicenseTemplate> {
}

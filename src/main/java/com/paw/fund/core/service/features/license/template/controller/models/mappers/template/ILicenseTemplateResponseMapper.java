package com.paw.fund.core.service.features.license.template.controller.models.mappers.template;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.section.ILicenseTemplateSectionRequestMapper;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.section.ILicenseTemplateSectionResponseMapper;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateRequest;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ILicenseTemplateSectionResponseMapper.class}
)
public interface ILicenseTemplateResponseMapper extends IModelMapper<LicenseTemplateResponse, LicenseTemplate> {
}

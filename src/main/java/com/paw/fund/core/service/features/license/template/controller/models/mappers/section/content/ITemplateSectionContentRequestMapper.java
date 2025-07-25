package com.paw.fund.core.service.features.license.template.controller.models.mappers.section.content;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.features.license.template.controller.models.section.content.TemplateSectionContentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ITemplateSectionContentRequestMapper extends IModelMapper<TemplateSectionContentRequest, TemplateSectionContent> {
}

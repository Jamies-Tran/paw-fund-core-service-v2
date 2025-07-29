package com.paw.fund.core.service.features.form.controller.models.option;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.form.option.Option;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IOptionResponseMapper extends IModelMapper<OptionResponse, Option> {
}

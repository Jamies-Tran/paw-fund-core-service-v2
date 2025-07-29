package com.paw.fund.core.service.features.form.controller.models;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.form.Form;
import com.paw.fund.core.service.features.form.controller.models.question.IQuestionResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IQuestionResponseMapper.class}
)
public interface IFormResponseMapper extends IModelMapper<FormResponse, Form> {
}

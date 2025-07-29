package com.paw.fund.core.service.features.form.controller.models.question;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.features.form.controller.models.option.IOptionRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IOptionRequestMapper.class}
)
public interface IQuestionRequestMapper extends IModelMapper<QuestionRequest, Question> {
}

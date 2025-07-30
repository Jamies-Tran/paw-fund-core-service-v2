package com.paw.fund.core.service.features.form.reply.controller.models.mappers.answer;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.form.answer.Answer;
import com.paw.fund.core.service.features.form.reply.controller.models.answer.AnswerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IAnswerRequestMapper extends IModelMapper<AnswerRequest, Answer> {
}

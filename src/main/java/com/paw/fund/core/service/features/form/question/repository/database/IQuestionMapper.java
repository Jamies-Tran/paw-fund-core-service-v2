package com.paw.fund.core.service.features.form.question.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.form.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IQuestionMapper extends IEntityMapper<QuestionEntity, Question> {
    void update(@MappingTarget QuestionEntity entity, Question dto);
}

package com.paw.fund.core.service.features.form.question.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotValid;
import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.bootstrap.utils.PSpringContext;
import com.paw.fund.core.service.domain.form.option.IOptionUseCase;
import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.enums.form.EQuestionType;
import com.paw.fund.core.service.features.form.question.repository.database.IQuestionMapper;
import com.paw.fund.core.service.features.form.question.repository.database.IQuestionRepository;
import com.paw.fund.core.service.features.form.question.repository.database.QuestionEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionCommandService {
    IQuestionRepository repository;

    IQuestionMapper mapper;

    @Lazy
    IOptionUseCase optionUseCase;

    protected void save(Long formId, List<Question> questions) {
        questions.forEach(question -> {
            validate(question);

            Long questionId = repository.save(mapper.toEntity(question.withFormId(formId)))
                    .getQuestionId();
            optionUseCase.save(questionId, PObjectUtils.defaultList(question.options()));
        });
    }

    protected void update(Long formId, List<Question> questions) {

        Map<Long, Question> questions1 = questions.stream()
                .filter(question -> PObjectUtils.isNotNull(question.questionId()))
                .collect(Collectors.toMap(Question::questionId, question -> question));
        repository.findAllByFormId(formId)
                .forEach(foundQuestion -> {
                    if (questions1.containsKey(foundQuestion.getQuestionId())) {
                        Question question = questions1.get(foundQuestion.getQuestionId());
                        validate(question);
                        mapper.update(foundQuestion, question);
                        optionUseCase.update(foundQuestion.getQuestionId(),
                                PObjectUtils.defaultList(question.options()));
                        repository.save(foundQuestion);
                    } else {
                        foundQuestion.setStatusCode(EDeleteStatus.DELETED.getCode());
                        foundQuestion.setStatusName(EDeleteStatus.DELETED.getName());
                        repository.save(foundQuestion);
                    }
                });
        questions.stream()
                .filter(question -> PObjectUtils.isNull(question.questionId()))
                .forEach(question -> {
                    validate(question);
                    QuestionEntity question1 = repository.save(mapper.toEntity(question.withFormId(formId)));
                    optionUseCase.save(question1.getQuestionId(), PObjectUtils.defaultList(question.options()));
                });
    }

    private void validate(Question question) {
        if (PObjectUtils.isNotEqual(EQuestionType.ESSAY.getCode(), question.questionTypeCode())
                && (PObjectUtils.isNull(question.options()) || question.options().size() < 2)) {
            throw new PResourceNotValid("Câu hỏi trắc nhiệm phải có ít nhất 2 lựa chọn");
        }
    }
}

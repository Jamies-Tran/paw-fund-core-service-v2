package com.paw.fund.core.service.features.form.question.service;

import com.paw.fund.core.service.domain.form.option.Option;
import com.paw.fund.core.service.domain.form.question.IQuestionUseCase;
import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.domain.form.question.QuestionPrivateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionUseCaseService extends QuestionPrivateService
        implements IQuestionUseCase {
    QuestionCommandService commandService;

    QuestionQueryService queryService;

    @Override
    @Transactional
    public void save(Long formId, List<Question> questions) {
        commandService.save(formId, questions);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> findAllByFormId(Long formId) {
        List<Question> questions = queryService.findAllByFormId(formId);
        Map<Long, List<Option>> options = options(questions.stream()
                .map(Question::questionId)
                .toList());

        return questions.stream()
                .map(question -> question.withOptions(options
                        .computeIfAbsent(question.questionId(), q -> List.of())))
                .toList();
    }

    @Override
    @Transactional
    public void update(Long formId, List<Question> questions) {
        commandService.update(formId, questions);
    }
}

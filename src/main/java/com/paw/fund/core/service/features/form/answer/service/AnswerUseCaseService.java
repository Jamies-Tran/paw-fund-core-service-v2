package com.paw.fund.core.service.features.form.answer.service;

import com.paw.fund.core.service.domain.form.answer.Answer;
import com.paw.fund.core.service.domain.form.answer.IAnswerUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerUseCaseService implements IAnswerUseCase {
    AnswerCommandService commandService;

    AnswerQueryService queryService;

    @Override
    @Transactional
    public void save(Long formId, List<Answer> answers) {
        commandService.save(formId, answers);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Answer> findAllByFormId(Long formId) {
        return queryService.findAllByFormReplyId(formId);
    }

    @Override
    @Transactional
    public void update(Long formId, List<Answer> answers) {
        commandService.update(formId, answers);
    }
}

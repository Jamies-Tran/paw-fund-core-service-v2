package com.paw.fund.core.service.features.form.option.service;

import com.paw.fund.core.service.domain.form.option.IOptionUseCase;
import com.paw.fund.core.service.domain.form.option.Option;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OptionUseCaseService implements IOptionUseCase {
    OptionCommandService commandService;

    OptionQueryService queryService;

    @Override
    @Transactional
    public void save(Long questionId, List<Option> options) {
        commandService.save(questionId, options);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Option> findAllByQuestionIdIn(List<Long> questionIds) {
        return queryService.findAllByQuestionIdIn(questionIds);
    }

    @Override
    @Transactional
    public void update(Long questionId, List<Option> options) {
        commandService.update(questionId, options);
    }
}

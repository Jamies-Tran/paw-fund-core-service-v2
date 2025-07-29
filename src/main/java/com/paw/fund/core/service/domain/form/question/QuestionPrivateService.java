package com.paw.fund.core.service.domain.form.question;

import com.paw.fund.core.service.domain.form.option.IOptionUseCase;
import com.paw.fund.core.service.domain.form.option.Option;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class QuestionPrivateService {
    @Autowired
    IOptionUseCase optionUseCase;

    protected Map<Long, List<Option>> options(List<Long> questionIds) {
        return optionUseCase.findAllByQuestionIdIn(questionIds)
                .stream()
                .collect(Collectors.groupingBy(Option::questionId));
    }
}

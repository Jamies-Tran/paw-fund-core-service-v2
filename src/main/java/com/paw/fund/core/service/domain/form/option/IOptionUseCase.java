package com.paw.fund.core.service.domain.form.option;

import java.util.List;

public interface IOptionUseCase {
    void save(Long questionId, List<Option> options);

    List<Option> findAllByQuestionIdIn(List<Long> questionIds);

    void update(Long questionId, List<Option> options);
}

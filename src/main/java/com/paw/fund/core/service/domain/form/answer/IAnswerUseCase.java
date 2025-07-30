package com.paw.fund.core.service.domain.form.answer;

import java.util.List;

public interface IAnswerUseCase {
    void save(Long formId, List<Answer> answers);

    List<Answer> findAllByFormId(Long formId);

    void update(Long formId, List<Answer> answers);
}

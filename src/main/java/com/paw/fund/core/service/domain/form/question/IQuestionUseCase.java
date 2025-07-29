package com.paw.fund.core.service.domain.form.question;

import java.util.List;

public interface IQuestionUseCase {
    void save(Long formId, List<Question> questions);

    List<Question> findAllByFormId(Long formId);

    void update(Long formId, List<Question> questions);
}

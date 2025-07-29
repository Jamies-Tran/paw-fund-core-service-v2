package com.paw.fund.core.service.features.form.question.service;

import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.features.form.question.repository.database.IQuestionMapper;
import com.paw.fund.core.service.features.form.question.repository.database.IQuestionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionQueryService {
    IQuestionRepository repository;

    IQuestionMapper mapper;

    protected List<Question> findAllByFormId(Long formId) {
        return mapper.toDto(repository.findAllByFormId(formId));
    }
}

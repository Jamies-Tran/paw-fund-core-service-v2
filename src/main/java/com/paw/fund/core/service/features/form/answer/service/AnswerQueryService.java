package com.paw.fund.core.service.features.form.answer.service;

import com.paw.fund.core.service.domain.form.answer.Answer;
import com.paw.fund.core.service.features.form.answer.repository.database.IAnswerMapper;
import com.paw.fund.core.service.features.form.answer.repository.database.IAnswerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerQueryService {
    IAnswerRepository repository;

    IAnswerMapper mapper;

    protected List<Answer> findAllByFormReplyId(Long formReplyId) {
        return mapper.toDto(repository.findAllByFormReplyId(formReplyId));
    }
}

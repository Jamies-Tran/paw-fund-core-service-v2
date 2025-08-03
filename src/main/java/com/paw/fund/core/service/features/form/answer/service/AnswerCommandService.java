package com.paw.fund.core.service.features.form.answer.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.answer.Answer;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.form.answer.repository.database.IAnswerMapper;
import com.paw.fund.core.service.features.form.answer.repository.database.IAnswerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerCommandService {
    IAnswerRepository repository;

    IAnswerMapper mapper;

    protected void save(Long formReplyId, List<Answer> answers) {
        repository.saveAll(mapper.toEntity(answers.stream()
                .map(a -> a.withFormReplyId(formReplyId)).toList()));
    }

    protected void update(Long formReplyId, List<Answer> answers) {
        repository.deleteAll(repository.findAllByFormReplyId(formReplyId));

        repository.saveAll(mapper.toEntity(answers.stream().map(answer -> answer
                .withFormReplyId(formReplyId)).toList()));
    }
}

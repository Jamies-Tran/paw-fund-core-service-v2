package com.paw.fund.core.service.features.form.option.service;

import com.paw.fund.core.service.domain.form.option.Option;
import com.paw.fund.core.service.features.form.option.repository.database.IOptionMapper;
import com.paw.fund.core.service.features.form.option.repository.database.IOptionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OptionQueryService {
    IOptionRepository repository;

    IOptionMapper mapper;

    protected List<Option> findAllByQuestionIdIn(List<Long> questionIds) {
        return mapper.toDto(repository.findAllByQuestionIdIn(questionIds));
    }
}

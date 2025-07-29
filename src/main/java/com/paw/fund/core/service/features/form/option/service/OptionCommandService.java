package com.paw.fund.core.service.features.form.option.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.option.Option;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.form.option.repository.database.IOptionMapper;
import com.paw.fund.core.service.features.form.option.repository.database.IOptionRepository;
import com.paw.fund.core.service.features.form.option.repository.database.OptionEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OptionCommandService {
    IOptionRepository repository;

    IOptionMapper mapper;

    protected void save(Long questionId, List<Option> options) {
        repository.saveAll(mapper.toEntity(options.stream()
                .map(opt -> opt.withQuestionId(questionId))
                .toList()));
    }

    protected void update(Long questionId, List<Option> options) {
        Map<Long, Option> options1 = options.stream()
                .filter(option -> PObjectUtils.isNotNull(option.optionId()))
                .collect(Collectors.toMap(Option::optionId, option -> option));

        repository.findAllByQuestionId(questionId)
                .forEach(foundOption -> {
                    if (options1.containsKey(foundOption.getOptionId())) {
                        mapper.update(foundOption, options1.get(foundOption.getOptionId()));
                        repository.save(foundOption);
                    } else {
                        foundOption.setStatusCode(EDeleteStatus.DELETED.getCode());
                        foundOption.setStatusName(EDeleteStatus.DELETED.getName());
                        repository.save(foundOption);
                    }
                });

        repository.saveAll(options.stream()
                .filter(option -> PObjectUtils.isNull(option.optionId()))
                .map(option -> mapper.toEntity(option.withQuestionId(questionId)))
                .toList());
    }
}

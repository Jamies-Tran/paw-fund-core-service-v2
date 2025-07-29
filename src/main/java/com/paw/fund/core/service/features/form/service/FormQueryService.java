package com.paw.fund.core.service.features.form.service;

import com.paw.fund.core.service.domain.form.Form;
import com.paw.fund.core.service.domain.form.FormCriteria;
import com.paw.fund.core.service.features.form.repository.database.IFormMapper;
import com.paw.fund.core.service.features.form.repository.database.IFormRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormQueryService {
    IFormRepository repository;

    IFormMapper mapper;

    protected Optional<Form> findById(Long formId) {
        return repository.findById(formId)
                .map(mapper::toDto);
    }

    protected Page<Form> findAll(FormCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDto);
    }
}

package com.paw.fund.core.service.domain.form;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface IFormUseCase {
    Long save(Form form);

    Optional<Form> findById(Long formId);

    void update(Long formId, Form form);

    void enable(Long formId);

    void disable(Long formId);

    void delete(Long formId);

    Page<Form> findAll(FormCriteria criteria, PageRequest pageRequest);
}

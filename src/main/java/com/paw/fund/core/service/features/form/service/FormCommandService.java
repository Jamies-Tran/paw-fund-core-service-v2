package com.paw.fund.core.service.features.form.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotValid;
import com.paw.fund.core.service.domain.form.Form;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.enums.form.EFormStatus;
import com.paw.fund.core.service.features.form.repository.database.IFormMapper;
import com.paw.fund.core.service.features.form.repository.database.IFormRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormCommandService {
    IFormRepository repository;

    IFormMapper mapper;

    protected Long save(Form form) {
        if (repository.existsByFormTypeCode(form.formTypeCode())) {
            throw new PResourceNotValid("Form %s đã tồn tại".formatted(form.formTypeName()));
        }

        return repository
                .save(mapper.toEntity(form))
                .getFormId();
    }

    protected void update(Long formId, Form form) {
        repository.findById(formId)
                .ifPresentOrElse(
                        form1 -> {
                            mapper.update(form1, form);
                            repository.save(form1);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }

    protected void delete(Long formId) {
        repository.findById(formId)
                .ifPresentOrElse(
                        foundForm -> {
                            foundForm.setStatusCode(EDeleteStatus.DELETED.getCode());
                            foundForm.setStatusName(EDeleteStatus.DELETED.getName());
                            repository.save(foundForm);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }

    protected void updateStatus(Long formId, EFormStatus status) {
        repository.findById(formId)
                .ifPresentOrElse(
                        foundForm -> {
                            foundForm.setStatusCode(status.getCode());
                            foundForm.setStatusName(status.getName());
                            repository.save(foundForm);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }
}

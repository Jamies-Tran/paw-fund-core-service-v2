package com.paw.fund.core.service.features.form.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.Form;
import com.paw.fund.core.service.domain.form.FormCriteria;
import com.paw.fund.core.service.domain.form.FormPrivateService;
import com.paw.fund.core.service.domain.form.IFormUseCase;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
import com.paw.fund.core.service.enums.form.EFormStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormUseCaseService extends FormPrivateService
        implements IFormUseCase {
    FormCommandService commandService;

    FormQueryService queryService;

    @Override
    @Transactional
    public Long save(Form form) {
        Long formId = commandService.save(form);
        saveQuestions(formId, form.questions());

        return formId;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Form> findById(Long formId) {
        return queryService.findById(formId)
                .map(form -> form.withQuestions(questions(formId)));
    }

    @Override
    @Transactional
    public void update(Long formId, Form form) {
        updateQuestions(formId, form.questions());
        commandService.update(formId, form);
    }

    @Override
    @Transactional
    public void enable(Long formId) {
        commandService.updateStatus(formId, EFormStatus.ENABLED);
    }

    @Override
    @Transactional
    public void disable(Long formId) {
        commandService.updateStatus(formId, EFormStatus.DISABLED);
    }

    @Override
    @Transactional
    public void delete(Long formId) {
        commandService.delete(formId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Form> findAll(FormCriteria criteria, PageRequest pageRequest) {
        List<Role> roles = loginAccount().roles();
        if (PObjectUtils.isNotEmptyList(roles) && roles.stream()
                .anyMatch(role -> PObjectUtils.isNotEqual(ERole.ADMIN.getCode(), role.roleCode())
                        && PObjectUtils.isNotEqual(ERole.SHELTER_OWNER.getCode(), role.roleCode()))) {
            criteria = criteria.withStatusCodes(List.of(EFormStatus.ENABLED.getCode()));
        }
        return queryService.findAll(criteria, pageRequest);
    }
}

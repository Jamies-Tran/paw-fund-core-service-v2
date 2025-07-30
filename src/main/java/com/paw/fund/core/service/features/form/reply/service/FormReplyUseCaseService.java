package com.paw.fund.core.service.features.form.reply.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.reply.FormReply;
import com.paw.fund.core.service.domain.form.reply.FormReplyCriteria;
import com.paw.fund.core.service.domain.form.reply.FormReplyDetail;
import com.paw.fund.core.service.domain.form.reply.FormReplyPrivateService;
import com.paw.fund.core.service.domain.form.reply.IFormReplyUseCase;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
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
public class FormReplyUseCaseService extends FormReplyPrivateService
        implements IFormReplyUseCase {
    FormReplyCommandService commandService;

    FormReplyQueryService queryService;

    @Override
    @Transactional
    public Long save(FormReply formReply) {
        Long accountId = loginAccount().accountId();
        Long formReplyId = commandService.save(formReply.withAccountId(accountId));
        saveAnswers(formReplyId, formReply.answers());

        return formReplyId;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormReplyDetail> findById(Long formReplyId) {
        return queryService.findById(formReplyId)
                .map(formReply -> formReply
                        .withAnswers(answers(formReplyId)))
                .map(FormReplyDetail::of);
    }

    @Override
    @Transactional
    public void update(Long formReplyId, FormReply formReply) {
        updateAnswers(formReplyId, formReply.answers());
        commandService.update(formReplyId, formReply);
    }

    @Override
    @Transactional
    public void delete(Long formReplyId) {
        commandService.delete(formReplyId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FormReply> findAll(FormReplyCriteria criteria, PageRequest pageRequest) {
        List<Role> roles = loginAccount().roles();
        if (roles.stream().anyMatch(role -> PObjectUtils.isNotEqual(ERole.ADMIN.getCode(), role.roleCode()))) {
            criteria = criteria.withAccountId(loginAccount().accountId());
        }
        return queryService.findAll(criteria, pageRequest);
    }
}

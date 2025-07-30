package com.paw.fund.core.service.features.form.reply.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.form.reply.FormReply;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.form.reply.repository.database.IFormReplyMapper;
import com.paw.fund.core.service.features.form.reply.repository.database.IFormReplyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormReplyCommandService {
    IFormReplyRepository repository;

    IFormReplyMapper mapper;

    protected Long save(FormReply formReply) {
        return repository.save(mapper.toEntity(formReply))
                .getFormReplyId();
    }

    protected void update(Long formReplyId, FormReply formReply) {
        repository.findById(formReplyId)
                .ifPresentOrElse(
                        foundFormReply -> {
                            mapper.update(foundFormReply, formReply);
                            repository.save(foundFormReply);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }

    protected void delete(Long formReplyId) {
        repository.findById(formReplyId)
                .ifPresentOrElse(
                        foundFormReply -> {
                            foundFormReply.setStatusCode(EDeleteStatus.DELETED.getCode());
                            foundFormReply.setStatusName(EDeleteStatus.DELETED.getName());
                            repository.save(foundFormReply);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }
}

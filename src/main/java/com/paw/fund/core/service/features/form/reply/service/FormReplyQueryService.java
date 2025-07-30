package com.paw.fund.core.service.features.form.reply.service;

import com.paw.fund.core.service.domain.form.reply.FormReply;
import com.paw.fund.core.service.domain.form.reply.FormReplyCriteria;
import com.paw.fund.core.service.features.form.reply.repository.database.IFormReplyMapper;
import com.paw.fund.core.service.features.form.reply.repository.database.IFormReplyRepository;
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
public class FormReplyQueryService {
    IFormReplyRepository repository;

    IFormReplyMapper mapper;

    protected Optional<FormReply> findById(Long formReplyId) {
        return repository.findById(formReplyId)
                .map(mapper::toDto);
    }

    protected Page<FormReply> findAll(FormReplyCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDto);
    }
}

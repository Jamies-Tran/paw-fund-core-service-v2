package com.paw.fund.core.service.domain.form.reply;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface IFormReplyUseCase {
    Long save(FormReply formReply);

    Optional<FormReplyDetail> findById(Long formReplyId);

    void update(Long formReplyId, FormReply formReply);

    void delete(Long formReplyId);

    Page<FormReply> findAll(FormReplyCriteria criteria, PageRequest pageRequest);
}

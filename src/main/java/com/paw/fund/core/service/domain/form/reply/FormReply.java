package com.paw.fund.core.service.domain.form.reply;

import com.paw.fund.core.service.domain.form.answer.Answer;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record FormReply(
        Long formReplyId,
        @With Long accountId,
        Long formId,
        String title,
        String description,
        @With List<Answer> answers
) {
}

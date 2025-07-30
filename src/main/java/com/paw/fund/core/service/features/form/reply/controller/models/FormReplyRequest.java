package com.paw.fund.core.service.features.form.reply.controller.models;

import com.paw.fund.core.service.features.form.reply.controller.models.answer.AnswerRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record FormReplyRequest(
        Long formId,
        String title,
        String description,
        List<AnswerRequest> answers
) {
}

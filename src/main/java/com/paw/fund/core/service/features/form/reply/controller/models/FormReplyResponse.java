package com.paw.fund.core.service.features.form.reply.controller.models;

import lombok.Builder;

@Builder
public record FormReplyResponse(
        Long formReplyId,
        Long accountId,
        Long formId,
        String title,
        String description
) {
}

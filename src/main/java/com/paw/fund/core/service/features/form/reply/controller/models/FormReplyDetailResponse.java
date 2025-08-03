package com.paw.fund.core.service.features.form.reply.controller.models;

import lombok.Builder;

import java.util.List;

@Builder
public record FormReplyDetailResponse(
        Long formReplyId,
        String title,
        String description,
        List<AnswerDetailResponse> answerContent
) {
    @Builder
    public record AnswerDetailResponse(
            String questionText,
            List<String> answerTexts
    ) {}
}

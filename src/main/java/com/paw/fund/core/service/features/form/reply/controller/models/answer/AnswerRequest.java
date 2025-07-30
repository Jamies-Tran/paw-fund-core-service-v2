package com.paw.fund.core.service.features.form.reply.controller.models.answer;

import java.util.List;

public record AnswerRequest(
        Long questionId,
        AnswerContentRequest answer
) {
    public record AnswerContentRequest (
            String questionText,
            String answerText
    ) {}
}

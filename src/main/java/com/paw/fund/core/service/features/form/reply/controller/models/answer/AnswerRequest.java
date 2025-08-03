package com.paw.fund.core.service.features.form.reply.controller.models.answer;

import java.util.List;

public record AnswerRequest(
        AnswerContentRequest answerContent
) {
    public record AnswerContentRequest (
            String questionText,
            List<String> answerTexts
    ) {}
}

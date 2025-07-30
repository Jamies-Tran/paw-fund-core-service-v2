package com.paw.fund.core.service.domain.form.answer;

import lombok.Builder;
import lombok.With;

@Builder
public record Answer(
        Long answerId,
        @With Long formReplyId,
        Long questionId,
        AnswerContent answer
) {
    public record AnswerContent(
            String questionText,
            String answerText
    ) {}
}

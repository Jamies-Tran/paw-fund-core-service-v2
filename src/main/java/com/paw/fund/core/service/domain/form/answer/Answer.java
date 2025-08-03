package com.paw.fund.core.service.domain.form.answer;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.enums.EEnableStatus;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record Answer(
        Long answerId,
        @With Long formReplyId,
        Long questionId,
        String statusCode,
        String statusName,
        AnswerContent answerContent
) {
    public record AnswerContent(
            String questionText,
            List<String> answerTexts
    ) {}

    public Answer {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

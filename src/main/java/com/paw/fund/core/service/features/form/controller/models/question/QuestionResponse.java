package com.paw.fund.core.service.features.form.controller.models.question;

import com.paw.fund.core.service.features.form.controller.models.option.OptionResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionResponse(
        Long questionId,
        Long formId,
        String questionText,
        String questionTypeCode,
        String questionTypeName,
        Boolean isMultipleChoice,
        String statusCode,
        String statusName,
        List<OptionResponse> options
) {
}

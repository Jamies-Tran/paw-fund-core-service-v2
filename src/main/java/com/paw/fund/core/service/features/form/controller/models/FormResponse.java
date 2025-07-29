package com.paw.fund.core.service.features.form.controller.models;

import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.features.form.controller.models.question.QuestionResponse;
import lombok.With;

import java.util.List;

public record FormResponse(
        Long formId,
        Long shelterId,
        String title,
        String description,
        Integer questionCount,
        String formTypeCode,
        String formTypeName,
        String statusCode,
        String statusName,
        List<QuestionResponse> questions
) {
}

package com.paw.fund.core.service.domain.form;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.enums.EEnableStatus;
import lombok.With;

import java.util.List;

public record Form(
        Long formId,
        Long shelterId,
        String title,
        String description,
        @With Integer questionCount,
        @With String formTypeCode,
        @With String formTypeName,
        String statusCode,
        String statusName,
        @With List<Question> questions
) {
    public Form {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

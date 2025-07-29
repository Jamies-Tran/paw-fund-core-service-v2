package com.paw.fund.core.service.domain.form.question;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.option.Option;
import com.paw.fund.core.service.enums.EEnableStatus;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record Question(
        Long questionId,
        @With Long formId,
        String questionText,
        String questionTypeCode,
        String questionTypeName,
        Boolean isMultipleChoice,
        String statusCode,
        String statusName,
        @With List<Option> options
) {
    public Question {
        if (PObjectUtils.isNull(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

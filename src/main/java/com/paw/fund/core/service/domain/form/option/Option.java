package com.paw.fund.core.service.domain.form.option;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.enums.EEnableStatus;
import lombok.Builder;
import lombok.With;

@Builder
public record Option(
        Long optionId,
        @With Long questionId,
        @With Long answerOptionId,
        String optionText,
        String statusCode,
        String statusName
) {
    public Option {
        if(PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

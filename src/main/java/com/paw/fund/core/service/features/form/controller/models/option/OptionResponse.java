package com.paw.fund.core.service.features.form.controller.models.option;

import lombok.Builder;
import lombok.With;

@Builder
public record OptionResponse(
        Long optionId,
        Long questionId,
        Long answerOptionId,
        String optionText,
        String statusCode,
        String statusName
) {
}

package com.paw.fund.core.service.features.form.controller.models.option;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@Builder
public record OptionRequest(
        Long optionId,
        @NotNull(message = "Vui lòng nhập thông tin lựa chọn")
        String optionText
) {
}

package com.paw.fund.core.service.features.account.license.controller.models.content;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AccountLicenseContentRequest(
        Long accountLicenseContentId,
        @NotNull(message = "Thiếu thông tin phần nội dung của giấy phép")
        Long templateSectionContentId,
        @NotNull(message = "Vui lòng nhập thông tin giấy phép")
        String content
) {
}

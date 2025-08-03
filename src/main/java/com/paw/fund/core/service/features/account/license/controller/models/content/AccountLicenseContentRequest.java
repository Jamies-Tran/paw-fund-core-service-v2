package com.paw.fund.core.service.features.account.license.controller.models.content;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AccountLicenseContentRequest(
        @NotNull(message = "Vui lòng nhập thông tin giấy phép")
        LicenseContentRequest content
) {
        public record LicenseContentRequest(
                String sectionContent,
                String licenseTemplateContent,
                String licenseContent
        ) {
        }
}

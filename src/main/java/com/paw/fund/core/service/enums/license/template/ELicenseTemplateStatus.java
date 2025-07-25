package com.paw.fund.core.service.enums.license.template;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ELicenseTemplateStatus {
    ENABLED("ENABLED", "Kích hoạt"),
    DISABLED("DISABLED", "Vô hiệu hóa"),;

    String code;
    String name;
}

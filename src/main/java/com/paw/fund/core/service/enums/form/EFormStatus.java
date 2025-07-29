package com.paw.fund.core.service.enums.form;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EFormStatus {
    ENABLED("ENABLED", "Kích hoạt"),
    DISABLED("DISABLED", "Vô hiệu hóa"),;

    String code;
    String name;
}

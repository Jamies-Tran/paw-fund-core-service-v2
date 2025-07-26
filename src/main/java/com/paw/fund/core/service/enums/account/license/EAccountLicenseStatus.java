package com.paw.fund.core.service.enums.account.license;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAccountLicenseStatus {
    ENABLE("ENABLE", "Kích hoạt");

    String code;
    String name;
}

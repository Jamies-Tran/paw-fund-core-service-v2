package com.paw.fund.core.service.enums.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAccountStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    INACTIVE("INACTIVE", "Chưa kích hoạt"),
    DISABLE("DISABLE", "Vô hiệu hóa");

    String code;
    String name;
}

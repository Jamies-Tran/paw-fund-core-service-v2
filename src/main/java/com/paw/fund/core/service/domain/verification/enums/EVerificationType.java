package com.paw.fund.core.service.domain.verification.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EVerificationType {
    ACCOUNT_CREATION("ACCOUNT_CREATION", "Xác minh tài khoản"),
    CHANGE_PASSWORD("CHANGE_PASSWORD", "Xác minh thay đổi mật khẩu"),
    CHANGE_EMAIL("CHANGE_EMAIL", "Xác minh thay đổi email");

    String code;
    String name;
}

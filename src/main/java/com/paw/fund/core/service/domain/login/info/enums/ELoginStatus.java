package com.paw.fund.core.service.domain.login.info.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ELoginStatus {
    LOGIN("LOGIN", "Login"),
    LOGOUT("LOGOUT", "Logout");

    String code;
    String name;
}

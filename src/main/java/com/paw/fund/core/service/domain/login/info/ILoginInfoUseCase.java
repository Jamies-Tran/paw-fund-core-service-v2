package com.paw.fund.core.service.domain.login.info;

import lombok.NonNull;

import java.math.BigDecimal;

public interface ILoginInfoUseCase {
    LoginInfo auth(
            @NonNull String email,
            @NonNull String password,
            BigDecimal latitude,
            BigDecimal longitude
    );

    LoginAccount getCurrentAccountLogin();

    void logout();
}

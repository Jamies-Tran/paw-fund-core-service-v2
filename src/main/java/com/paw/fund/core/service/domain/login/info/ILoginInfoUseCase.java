package com.paw.fund.core.service.domain.login.info;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Optional;

public interface ILoginInfoUseCase {
    LoginInfo auth(
            @NonNull String email,
            @NonNull String password,
            BigDecimal latitude,
            BigDecimal longitude
    );

    Optional<LoginAccount> getCurrentAccountLogin();

    void logout();
}

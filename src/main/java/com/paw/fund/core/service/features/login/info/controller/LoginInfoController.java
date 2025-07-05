package com.paw.fund.core.service.features.login.info.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.features.login.info.repository.database.ILoginInfoMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoController implements ILoginInfoApi {
    ILoginInfoUseCase loginInfoUseCase;

    @Override
    public PValueResponse<?> logout() {
        loginInfoUseCase.logout();

        return PValueResponse.successNoData();
    }
}

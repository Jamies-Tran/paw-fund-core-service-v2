package com.paw.fund.core.service.features.login.info.controller.pub;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginInfo;
import com.paw.fund.core.service.features.login.info.controller.models.LoginInfoRequest;
import com.paw.fund.core.service.features.login.info.controller.models.LoginInfoResponse;
import com.paw.fund.core.service.features.login.info.controller.models.mapper.ILoginInfoResponseModelMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoPubController implements ILoginInfoPubApi {
    ILoginInfoUseCase loginInfoUseCase;

    ILoginInfoResponseModelMapper responseModelMapper;

    @Override
    public PValueResponse<LoginInfoResponse> auth(LoginInfoRequest request) {
        LoginInfo loginInfo = loginInfoUseCase.auth(
                request.email(),
                request.password(),
                request.latitude(),
                request.longitude()
        );

        return PValueResponse.success(responseModelMapper.toModel(loginInfo));
    }
}

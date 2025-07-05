package com.paw.fund.core.service.features.login.info.controller.pub;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.login.info.controller.models.LoginInfoRequest;
import com.paw.fund.core.service.features.login.info.controller.models.LoginInfoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/pub/auth")
@Tag(name = "Auth", description = "QL xác thực tài khoản")
public interface ILoginInfoPubApi {
    @PostMapping
    PValueResponse<LoginInfoResponse> auth(@RequestBody @Valid LoginInfoRequest request);
}

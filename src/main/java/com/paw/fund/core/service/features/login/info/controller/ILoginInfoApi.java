package com.paw.fund.core.service.features.login.info.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/auth")
@Tag(name = "Auth", description = "QL xác thực tài khoản")
public interface ILoginInfoApi {
    @PatchMapping("/logout")
    PValueResponse<?> logout();
}

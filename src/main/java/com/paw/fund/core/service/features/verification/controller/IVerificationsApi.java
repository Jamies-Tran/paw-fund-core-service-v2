package com.paw.fund.core.service.features.verification.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.verification.controller.models.EmailRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/pub/verification")
@Tag(name = "Verification", description = "QL xác thực")
public interface IVerificationsApi {
    @PostMapping("/account")
    PValueResponse<?> sendVerification(@RequestBody @Valid EmailRequest emailRequest);
}

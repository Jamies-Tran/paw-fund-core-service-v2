package com.paw.fund.core.service.features.account.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/account/{accountId}")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountApi {
    @PatchMapping("/disable") @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHELTER_OWNER')")
    PValueResponse<?> disable(@PathVariable Long accountId);

    @PatchMapping("/enable") @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHELTER_OWNER')")
    PValueResponse<?> enable(@PathVariable Long accountId);
}

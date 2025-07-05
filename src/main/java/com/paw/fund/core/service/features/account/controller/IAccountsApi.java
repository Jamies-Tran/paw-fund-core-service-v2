package com.paw.fund.core.service.features.account.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/account")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountsApi {
    @PostMapping("/staff/{shelterId}") @PreAuthorize("hasRole('ROLE_SHELTER_OWNER')")
    PValueResponse<Long> saveStaff(
            @RequestBody @Valid AccountRequest accountRequest,
            @PathVariable("shelterId") Long shelterId
    );
}

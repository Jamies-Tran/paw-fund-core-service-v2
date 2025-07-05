package com.paw.fund.core.service.features.account.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/account/{accountId}")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountApi {
    @PutMapping
    PValueResponse<?> updateAccountId(
            @PathVariable
            Long accountId,

            @RequestBody
            @Valid
            AccountRequest accountRequest
    );
}

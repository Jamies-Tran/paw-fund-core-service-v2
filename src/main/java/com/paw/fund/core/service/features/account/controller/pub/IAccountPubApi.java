package com.paw.fund.core.service.features.account.controller.pub;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.account.controller.models.AccountResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/pub/account/{accountId}")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountPubApi {
    @GetMapping
    PValueResponse<AccountResponse> findById(@PathVariable Long accountId);
}

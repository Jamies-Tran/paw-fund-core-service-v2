package com.paw.fund.core.service.features.account.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import com.paw.fund.core.service.features.account.controller.models.mapper.IAccountRequestModelMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AccountController implements IAccountApi {
    IAccountUseCase accountUseCase;

    IAccountRequestModelMapper requestModelMapper;

    @Override
    public PValueResponse<?> updateAccountId(Long accountId, AccountRequest accountRequest) {
        Account account = requestModelMapper.toDto(accountRequest);
        accountUseCase.update(accountId, account);

        return PValueResponse.successNoData();
    }
}

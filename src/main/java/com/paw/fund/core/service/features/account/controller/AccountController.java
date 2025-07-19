package com.paw.fund.core.service.features.account.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AccountController implements IAccountApi {
    IAccountUseCase accountUseCase;

    @Override
    public PValueResponse<?> disable(Long accountId) {
        accountUseCase.updateStatus(accountId, EAccountStatus.DISABLE);

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> enable(Long accountId) {
        accountUseCase.updateStatus(accountId, EAccountStatus.ACTIVE);

        return PValueResponse.successNoData();
    }
}

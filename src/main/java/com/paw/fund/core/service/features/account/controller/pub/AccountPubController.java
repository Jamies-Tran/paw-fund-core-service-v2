package com.paw.fund.core.service.features.account.controller.pub;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import com.paw.fund.core.service.enums.account.EAccountStatus;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import com.paw.fund.core.service.features.account.controller.models.AccountResponse;
import com.paw.fund.core.service.features.account.controller.models.UpdateStatusRequest;
import com.paw.fund.core.service.features.account.controller.models.mapper.IAccountRequestModelMapper;
import com.paw.fund.core.service.features.account.controller.models.mapper.IAccountResponseModelMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountPubController implements IAccountPubApi {
    IAccountUseCase accountUseCase;

    IAccountResponseModelMapper responseModelMapper;

    IAccountRequestModelMapper requestModelMapper;

    @Override
    public PValueResponse<AccountResponse> findById(Long accountId) {
        Account account = accountUseCase.findByAccountId(accountId)
                .orElseThrow(PResourceNotFoundException::new);

        return PValueResponse.success(responseModelMapper.toModel(account));
    }

    @Override
    public PValueResponse<?> update(Long accountId, AccountRequest accountRequest) {
        Account account = requestModelMapper.toDto(accountRequest);
        accountUseCase.update(accountId, account);

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> active(Long accountId, UpdateStatusRequest updateStatusRequest) {
        accountUseCase.updateStatus(
                updateStatusRequest.verificationCode(),
                accountId,
                EAccountStatus.ACTIVE
        );

        return PValueResponse.successNoData();
    }
}

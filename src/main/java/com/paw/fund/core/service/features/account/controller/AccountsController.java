package com.paw.fund.core.service.features.account.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import com.paw.fund.core.service.domain.role.enums.ERole;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import com.paw.fund.core.service.features.account.controller.models.mapper.IAccountRequestModelMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AccountsController implements IAccountsApi {
    IAccountUseCase accountUseCase;

    IAccountRequestModelMapper modelMapper;

    @Override
    public PValueResponse<Long> saveStaff(AccountRequest accountRequest, Long shelterId) {
        Long accountId = accountUseCase.save(modelMapper.toDto(accountRequest), ERole.SHELTER_OWNER);

        return PValueResponse.success(accountId);
    }
}

package com.paw.fund.core.service.features.account.license.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PPageResponse;
import com.paw.fund.core.service.bootstrap.config.rest.PSorter;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.account.license.AccountLicenseCriteria;
import com.paw.fund.core.service.domain.account.license.IAccountLicenseUseCase;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseRequest;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseResponse;
import com.paw.fund.core.service.features.account.license.controller.models.IAccountLicenseRequestMapper;
import com.paw.fund.core.service.features.account.license.controller.models.IAccountLicenseResponseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicensesController implements IAccountLicensesApi {
    IAccountLicenseUseCase accountLicenseUseCase;

    IAccountLicenseRequestMapper requestMapper;

    IAccountLicenseResponseMapper responseMapper;

    @Override
    public PValueResponse<Long> save(AccountLicenseRequest request) {
        return PValueResponse.success(accountLicenseUseCase
                .save(requestMapper.toDto(request)));
    }

    @Override
    public PPageResponse<AccountLicenseResponse> findAll(
            List<LocalDateTime> timeRange,
            Long accountId,
            String sorter, Integer current, Integer pageSize
    ) {
        AccountLicenseCriteria criteria = AccountLicenseCriteria.of(timeRange, accountId);
        PageRequest pageRequest = PageRequest.of(current, pageSize, PSorter.of(sorter));
        Page<AccountLicenseResponse> responses = accountLicenseUseCase.findAll(criteria, pageRequest)
                .map(responseMapper::toModel);

        return PPageResponse.success(responses);
    }
}

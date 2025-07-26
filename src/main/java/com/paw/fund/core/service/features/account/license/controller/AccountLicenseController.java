package com.paw.fund.core.service.features.account.license.controller;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.account.license.AccountLicense;
import com.paw.fund.core.service.domain.account.license.AccountLicenseDetail;
import com.paw.fund.core.service.domain.account.license.IAccountLicenseUseCase;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseDetailResponse;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseRequest;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseResponse;
import com.paw.fund.core.service.features.account.license.controller.models.IAccountLicenseRequestMapper;
import com.paw.fund.core.service.features.account.license.controller.models.IAccountLicenseResponseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseController implements IAccountLicenseApi {
    IAccountLicenseUseCase accountLicenseUseCase;

    IAccountLicenseRequestMapper requestMapper;

    IAccountLicenseResponseMapper responseMapper;

    @Override
    public PValueResponse<AccountLicenseDetailResponse> findById(Long accountLicenseId) {
        AccountLicenseDetail accountLicense = accountLicenseUseCase.findById(accountLicenseId)
                .orElseThrow(PResourceNotFoundException::new);

        return PValueResponse.success(responseMapper.toModel(accountLicense));
    }

    @Override
    public PValueResponse<?> update(Long accountLicenseId, AccountLicenseRequest request) {
        accountLicenseUseCase.update(accountLicenseId, requestMapper.toDto(request));

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> delete(Long accountLicenseId) {
        accountLicenseUseCase.delete(accountLicenseId);

        return PValueResponse.successNoData();
    }
}

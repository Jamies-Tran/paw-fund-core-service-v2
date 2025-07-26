package com.paw.fund.core.service.features.account.license.content.service;

import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContentPrivateService;
import com.paw.fund.core.service.domain.account.license.content.IAccountLicenseContentUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseContentUseCaseService extends AccountLicenseContentPrivateService
        implements IAccountLicenseContentUseCase {
    AccountLicenseContentCommandService commandService;

    AccountLicenseContentQueryService queryService;

    @Override
    @Transactional
    public void save(Long accountLicenseId, List<AccountLicenseContent> contents) {
        commandService.save(accountLicenseId, contents);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountLicenseContent> findAllByAccountLicenseId(Long accountLicenseId) {

        return queryService.findAllByAccountLicenseId(accountLicenseId);
    }

    @Override
    @Transactional
    public void update(Long accountLicenseId, List<AccountLicenseContent> contents) {
        commandService.update(accountLicenseId, contents);
    }
}

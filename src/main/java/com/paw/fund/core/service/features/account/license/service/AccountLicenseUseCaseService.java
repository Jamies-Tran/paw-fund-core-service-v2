package com.paw.fund.core.service.features.account.license.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.account.license.AccountLicense;
import com.paw.fund.core.service.domain.account.license.AccountLicenseCriteria;
import com.paw.fund.core.service.domain.account.license.AccountLicenseDetail;
import com.paw.fund.core.service.domain.account.license.AccountLicensePrivateService;
import com.paw.fund.core.service.domain.account.license.IAccountLicenseUseCase;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseUseCaseService extends AccountLicensePrivateService
        implements IAccountLicenseUseCase {

    AccountLicenseCommandService commandService;

    AccountLicenseQueryService queryService;

    @Override
    @Transactional
    public Long save(AccountLicense accountLicense) {
        Long accountLicenseId = commandService.save(accountLicense
                .withAccountId(loginAccount().accountId()));
        saveContents(accountLicenseId, accountLicense.contents());

        return accountLicenseId;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountLicenseDetail> findById(Long accountLicenseId) {
        return queryService.findById(accountLicenseId)
                .map(accountLicense -> AccountLicenseDetail.from(licenseTemplate(accountLicense.licenseTemplateId()), accountLicense
                            .withContents(contents(accountLicense.accountLicenseId()))));
    }

    @Override
    @Transactional
    public void update(Long accountLicenseId, AccountLicense accountLicense) {
        updateContents(accountLicenseId, accountLicense.contents());
        commandService.update(accountLicenseId, accountLicense);
    }

    @Override
    @Transactional
    public void delete(Long accountLicenseId) {
        commandService.delete(accountLicenseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountLicense> findAll(AccountLicenseCriteria criteria, PageRequest pageRequest) {
        List<Role> roles = loginAccount().roles();
        if (PObjectUtils.isNotEmptyList(roles)
                && roles.stream().anyMatch(role -> PObjectUtils.isNotEqual(ERole.ADMIN.getCode(), role.roleCode()))) {
            criteria = criteria.withAccountId(loginAccount().accountId());
        }

        return queryService.findAll(criteria, pageRequest);
    }


}

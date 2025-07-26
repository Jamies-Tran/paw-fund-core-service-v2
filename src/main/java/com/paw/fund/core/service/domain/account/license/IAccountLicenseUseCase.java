package com.paw.fund.core.service.domain.account.license;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface IAccountLicenseUseCase {
    Long save(AccountLicense accountLicense);

    Optional<AccountLicenseDetail> findById(Long accountLicenseId);

    void update(Long accountLicenseId, AccountLicense accountLicense);

    void delete(Long accountLicenseId);

    Page<AccountLicense> findAll(AccountLicenseCriteria criteria, PageRequest pageRequest);
}

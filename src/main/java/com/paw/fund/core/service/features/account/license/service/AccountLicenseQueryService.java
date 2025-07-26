package com.paw.fund.core.service.features.account.license.service;

import com.paw.fund.core.service.domain.account.license.AccountLicense;
import com.paw.fund.core.service.domain.account.license.AccountLicenseCriteria;
import com.paw.fund.core.service.features.account.license.repository.database.IAccountLicenseMapper;
import com.paw.fund.core.service.features.account.license.repository.database.IAccountLicenseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseQueryService {
    IAccountLicenseRepository repository;

    IAccountLicenseMapper mapper;

    protected Optional<AccountLicense> findById(Long accountLicenseId) {
        return repository.findById(accountLicenseId)
                .map(mapper::toDto);
    }

    protected Page<AccountLicense> findAll(AccountLicenseCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDto);
    }
}

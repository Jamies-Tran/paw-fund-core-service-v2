package com.paw.fund.core.service.features.account.license.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.account.license.AccountLicense;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.account.license.repository.database.IAccountLicenseMapper;
import com.paw.fund.core.service.features.account.license.repository.database.IAccountLicenseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseCommandService {
    IAccountLicenseRepository repository;

    IAccountLicenseMapper mapper;

    protected Long save(AccountLicense accountLicense) {
        return repository
                .save(mapper.toEntity(accountLicense))
                .getAccountLicenseId();
    }

    protected void update(Long accountLicenseId, AccountLicense accountLicense) {
        repository.findById(accountLicenseId)
                .ifPresentOrElse(
                        accountLicense1 -> {
                            mapper.update(accountLicense1, accountLicense);
                            repository.save(accountLicense1);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }

    protected void delete(Long accountLicenseId) {
        repository.findById(accountLicenseId)
                .ifPresentOrElse(
                        accountLicense -> {
                            accountLicense.setStatusCode(EDeleteStatus.DELETED.getCode());
                            accountLicense.setStatusName(EDeleteStatus.DELETED.getName());
                            repository.save(accountLicense);
                        },
                        PResourceNotFoundException::new
                );
    }
}

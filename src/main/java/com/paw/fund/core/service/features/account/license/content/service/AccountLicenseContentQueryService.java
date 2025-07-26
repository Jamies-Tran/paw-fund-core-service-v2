package com.paw.fund.core.service.features.account.license.content.service;

import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.features.account.license.content.repository.database.IAccountLicenseContentMapper;
import com.paw.fund.core.service.features.account.license.content.repository.database.IAccountLicenseContentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseContentQueryService {
    IAccountLicenseContentRepository repository;

    IAccountLicenseContentMapper mapper;

    protected List<AccountLicenseContent> findAllByAccountLicenseId(Long accountLicenseId) {
        return mapper.toDto(repository.findAllByAccountLicenseId(accountLicenseId));
    }
}

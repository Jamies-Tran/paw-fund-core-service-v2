package com.paw.fund.core.service.features.account.license.content.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.features.account.license.content.repository.database.IAccountLicenseContentMapper;
import com.paw.fund.core.service.features.account.license.content.repository.database.IAccountLicenseContentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLicenseContentCommandService {
    IAccountLicenseContentRepository repository;

    IAccountLicenseContentMapper mapper;

    protected void save(Long accountLicenseId, List<AccountLicenseContent> contents) {
        repository.saveAll(mapper.toEntity(contents.stream()
                .map(content -> content.withAccountLicenseId(accountLicenseId))
                .toList()));
    }

    protected void update(Long accountLicenseId, List<AccountLicenseContent> contents) {
        repository.deleteAll(repository.findAllByAccountLicenseId(accountLicenseId));

        repository.saveAll(mapper.toEntity(contents.stream()
                .map(content -> content.withAccountLicenseId(accountLicenseId))
                .toList()));
    }
}

package com.paw.fund.core.service.features.account.service;

import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.features.account.repository.database.IAccountMapper;
import com.paw.fund.core.service.features.account.repository.database.IAccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    IAccountRepository repository;

    IAccountMapper mapper;

    protected Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toDto);
    }

    protected Boolean verifyCode(String verificationCode) {
        return repository.existsByVerificationCode(verificationCode);
    }

    protected Optional<Account> findById(Long accountId) {
        return repository.findById(accountId)
                .map(mapper::toDto);
    }
}

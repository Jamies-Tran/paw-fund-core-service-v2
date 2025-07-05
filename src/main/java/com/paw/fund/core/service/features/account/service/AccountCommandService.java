package com.paw.fund.core.service.features.account.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceDuplicateException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.AccountPrivateService;
import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import com.paw.fund.core.service.features.account.repository.database.AccountEntity;
import com.paw.fund.core.service.features.account.repository.database.IAccountMapper;
import com.paw.fund.core.service.features.account.repository.database.IAccountRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService extends AccountPrivateService {
    IAccountRepository repository;

    IAccountMapper mapper;

    protected Long save(@NonNull Account account) {
        validateSave(account);
        AccountEntity entity = mapper.toEntity(account);
        repository.save(entity);

        return entity.getAccountId();
    }

    protected void updateStatus(@NonNull String email, @NonNull EAccountStatus status) {
        repository.findByEmail(email)
                .ifPresentOrElse(
                        account -> {
                            account.setStatusCode(status.getCode());
                            account.setStatusName(status.getName());
                            repository.save(account);
                        },
                        PResourceNotFoundException::new
                );
    }

    protected void update(@NonNull Long accountId, @NonNull Account account) {
        repository.findById(accountId)
                .ifPresentOrElse(
                        account1 -> {
                            validateUpdate(account1, account);
                            mapper.update(account1, account);
                            repository.save(account1);
                        },
                        PResourceNotFoundException::new
                );
    }

    @Override
    protected void validateSave(Account account) {
        if(repository.existsByEmail(account.email())) {
            throw new PResourceDuplicateException("Email đã tồn tại");
        }

        if(repository.existsByPhone(account.phone())) {
            throw new PResourceDuplicateException("Số điện thoại đã tồn tại");
        }

        if(repository.existsByIdentification(account.identification())) {
            throw new PResourceDuplicateException("CCCD đã tồn tại");
        }
    }

    @Override
    protected void validateUpdate(AccountEntity foundAccount, Account account) {
        if(PObjectUtils.isNotEqual(foundAccount.getEmail(), account.email())
                && repository.existsByEmail(account.email())) {
            throw new PResourceDuplicateException("Email đã tồn tại");
        }

        if(PObjectUtils.isNotEqual(foundAccount.getPhone(), account.phone())
                && repository.existsByPhone(account.phone())) {
            throw new PResourceDuplicateException("Số điện thoại đã tồn tại");
        }

        if(PObjectUtils.isNotEqual(foundAccount.getIdentification(), account.identification())
                && repository.existsByIdentification(account.identification())) {
            throw new PResourceDuplicateException("CCCD đã tồn tại");
        }
    }
}

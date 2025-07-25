package com.paw.fund.core.service.features.account.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceDuplicateException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.bootstrap.utils.PPasswordEncoder;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.AccountCommandPrivateService;
import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
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
public class AccountCommandService extends AccountCommandPrivateService {
    IAccountRepository repository;

    IAccountMapper mapper;

    protected Long save(@NonNull Account account) {
        validateSave(account);
        AccountEntity entity = mapper.toEntity(account);
        repository.save(entity);

        return entity.getAccountId();
    }

    protected void updateStatus(@NonNull Long accountId, @NonNull EAccountStatus status) {
        repository.findById(accountId)
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
                            mapper.updateWithEmail(account1, account);
                            repository.save(account1);
                        },
                        PResourceNotFoundException::new
                );
    }

    protected void updatePassword(@NonNull String verificationCode, @NonNull String newPassword) {
        repository.findByVerificationCode(verificationCode, EVerificationType.CHANGE_PASSWORD)
                .ifPresentOrElse(
                        account -> {
                            account.setPassword(PPasswordEncoder.passwordEncoder().encode(newPassword));
                            repository.save(account);
                        },
                        PResourceNotFoundException::new
                );
    }

    protected void updateEmail(@NonNull String verificationCode) {
        repository.findDataHolderByVerificationCode(verificationCode)
                .ifPresentOrElse(
                        accVerification -> {
                            repository.findById(accVerification.getAccountId())
                                    .ifPresentOrElse(
                                            account -> {
                                                account.setEmail(accVerification.getDataHolder());
                                                repository.save(account);
                                            },
                                            PResourceNotFoundException::new
                                    );
                        },
                        PResourceNotFoundException::new
                );
    }

    @Override
    protected void validateSave(Account account) {
        if(repository.existsByEmailAndEmailNotNull(account.email())) {
            throw new PResourceDuplicateException("Email đã tồn tại");
        }

        if(repository.existsByPhoneAndPhoneNotNull(account.phone())) {
            throw new PResourceDuplicateException("Số điện thoại đã tồn tại");
        }

        if(repository.existsByIdentificationAndIdentificationNotNull(account.identification())) {
            throw new PResourceDuplicateException("CCCD đã tồn tại");
        }
    }



    @Override
    protected void validateUpdate(AccountEntity foundAccount, Account account) {
        if(PObjectUtils.isNotEqual(foundAccount.getEmail(), account.email())
                && repository.existsByEmailAndEmailNotNull(account.email())) {
            throw new PResourceDuplicateException("Email đã tồn tại");
        }

        if(PObjectUtils.isNotEqual(foundAccount.getPhone(), account.phone())
                && repository.existsByPhoneAndPhoneNotNull(account.phone())) {
            throw new PResourceDuplicateException("Số điện thoại đã tồn tại");
        }

        if(PObjectUtils.isNotEqual(foundAccount.getIdentification(), account.identification())
                && repository.existsByIdentificationAndIdentificationNotNull(account.identification())) {
            throw new PResourceDuplicateException("CCCD đã tồn tại");
        }
    }
}

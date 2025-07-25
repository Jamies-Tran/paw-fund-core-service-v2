package com.paw.fund.core.service.features.account.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.AccountPrivateService;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.domain.media.IMediaUseCase;
import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
import com.paw.fund.core.service.domain.verification.VerificationDeleteEventListener;
import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService extends AccountPrivateService
        implements IAccountUseCase {
    AccountCommandService commandService;

    AccountQueryService queryService;

    @Override
    @Transactional
    public Long save(@NonNull Account account, @NonNull ERole role,@NonNull Long shelterId) {
        Long accountId = commandService.save(account);
        saveMedia(accountId, account.medias());
        saveAccountRole(accountId, shelterId, role);

        return accountId;
    }

    @Override
    @Transactional
    public Long save(@NonNull Account account, @NonNull ERole role) {
        Long accountId = commandService.save(account);
        saveMedia(accountId, account.medias());
        saveAccountRole(accountId, role);

        return accountId;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByEmail(@NonNull String email) {
        return queryService.findByEmail(email)
                .map(account -> {
                    List<Role> roles = roles(account.accountId());
                    return account.withRoles(roles);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByAccountId(@NonNull Long accountId) {
        return queryService.findById(accountId)
                .map(account -> {
                    List<Media> medias = medias(account.accountId());
                    List<Role> roles = roles(account.accountId());

                    return account.withMedias(medias).withRoles(roles);
                });
    }

    @Override
    @Transactional
    public void updateStatus(
            @NonNull String verificationCode,
            @NonNull Long accountId,
            @NonNull EAccountStatus status
    ) {
        if (!queryService.verifyCode(accountId, verificationCode, EVerificationType.ACCOUNT_CREATION)) {
            throw new PResourceNotFoundException("Mã xác nhận không đúng!");
        }
        commandService.updateStatus(accountId, status);
        deleteVerification(verificationCode);
    }

    @Override
    public void updateStatus(@NonNull Long accountId, @NonNull EAccountStatus status) {
        commandService.updateStatus(accountId, status);
    }

    @Override
    @Transactional
    public void update(@NonNull Long accountId, @NonNull Account account) {
        commandService.update(accountId, account);
    }

    @Override
    @Transactional
    public void update(@NonNull Account account) {

        commandService.update(loginAccount().accountId(), account);
    }

    @Override
    @Transactional
    public void updatePassword(@NonNull String verificationCode, @NonNull String newPassword) {
        commandService.updatePassword(verificationCode, newPassword);
        deleteVerification(verificationCode);
    }

    @Override
    @Transactional
    public void updateEmail(@NonNull String verificationCode) {
        commandService.updateEmail(verificationCode);
        deleteVerification(verificationCode);
    }
}

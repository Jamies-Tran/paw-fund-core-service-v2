package com.paw.fund.core.service.domain.account;

import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import com.paw.fund.core.service.domain.role.enums.ERole;
import lombok.NonNull;

import java.util.Optional;

public interface IAccountUseCase {
    Long save(@NonNull Account account, @NonNull ERole role,@NonNull Long shelterId);

    Long save(@NonNull Account account, @NonNull ERole role);

    Optional<Account> findByEmail(@NonNull String email);

    Optional<Account> findByAccountId(@NonNull Long accountId);

    void updateStatus(
            @NonNull String verificationCode,
            @NonNull String mail,
            @NonNull EAccountStatus status
    );


    void update(@NonNull Long accountId, @NonNull Account account);
}

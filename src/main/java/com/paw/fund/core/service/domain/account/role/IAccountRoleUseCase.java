package com.paw.fund.core.service.domain.account.role;

import com.paw.fund.core.service.domain.role.enums.ERole;
import lombok.NonNull;

public interface IAccountRoleUseCase {
    Long save(@NonNull Long accountId, @NonNull ERole role, Long shelterId);
}

package com.paw.fund.core.service.domain.account.role;

import lombok.NonNull;

public interface IAccountRoleUseCase {
    Long save(@NonNull AccountRoleEventListener eventListener);
}

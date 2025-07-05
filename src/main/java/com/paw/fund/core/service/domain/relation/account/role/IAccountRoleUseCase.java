package com.paw.fund.core.service.domain.relation.account.role;

import lombok.NonNull;

public interface IAccountRoleUseCase {
    Long save(@NonNull AccountRoleEventListener eventListener);
}

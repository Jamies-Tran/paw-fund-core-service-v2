package com.paw.fund.core.service.domain.role;

import java.util.List;
import java.util.Optional;

public interface IRoleUseCase {
    Optional<Role> findByCode(String code);

    List<Role> findAllByAccountId(Long accountId);
}

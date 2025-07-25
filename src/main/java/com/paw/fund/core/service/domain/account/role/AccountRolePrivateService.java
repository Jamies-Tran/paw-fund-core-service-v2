package com.paw.fund.core.service.domain.account.role;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccountRolePrivateService {
    @Autowired
    IRoleUseCase roleUseCase;

    protected Role role(String roleCode) {
        return roleUseCase.findByCode(roleCode)
                .orElseThrow(PResourceNotFoundException::new);
    }
}

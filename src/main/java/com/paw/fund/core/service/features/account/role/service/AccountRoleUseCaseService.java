package com.paw.fund.core.service.features.account.role.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.account.role.AccountRoleEventListener;
import com.paw.fund.core.service.domain.account.role.IAccountRoleUseCase;

import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleUseCaseService implements IAccountRoleUseCase {
    AccountRoleCommandService commandService;

    IRoleUseCase roleUseCase;

    @Override
    @Transactional
    @EventListener
    public Long save(@NonNull AccountRoleEventListener eventListener) {
        Role role = roleUseCase.findByCode(eventListener.getRoleCode())
                .orElseThrow(PResourceNotFoundException::new);

        return commandService.save(eventListener.getAccountId(), role.roleId());
    }
}

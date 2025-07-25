package com.paw.fund.core.service.features.account.role.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.account.role.AccountRolePrivateService;
import com.paw.fund.core.service.domain.account.role.IAccountRoleUseCase;

import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
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
public class AccountRoleUseCaseService extends AccountRolePrivateService
        implements IAccountRoleUseCase {
    AccountRoleCommandService commandService;

    @Override
    @Transactional
    public Long save(@NonNull Long accountId, @NonNull ERole role, Long shelterId) {
        Role foundRole = role(role.getCode());

        return commandService.save(accountId, foundRole.roleId(), shelterId);
    }
}

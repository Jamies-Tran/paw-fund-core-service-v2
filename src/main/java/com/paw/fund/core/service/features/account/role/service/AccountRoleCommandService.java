package com.paw.fund.core.service.features.account.role.service;

import com.paw.fund.core.service.domain.account.role.AccountRole;
import com.paw.fund.core.service.features.account.role.repository.database.AccountRoleEntity;
import com.paw.fund.core.service.features.account.role.repository.database.IAccountRoleMapper;
import com.paw.fund.core.service.features.account.role.repository.database.IAccountRoleRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleCommandService {
    IAccountRoleRepository repository;

    IAccountRoleMapper mapper;

    protected Long save(@NonNull Long accountId, @NonNull Long roleId) {
        AccountRole accountRole = AccountRole.builder()
                .accountId(accountId)
                .roleId(roleId)
                .build();
        AccountRoleEntity save = mapper.toEntity(accountRole);
        repository.save(save);

        return save.getAccountRoleId();
    }
}

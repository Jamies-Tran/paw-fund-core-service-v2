package com.paw.fund.core.service.features.role.service;

import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleUseCaseService implements IRoleUseCase {
    RoleQueryService queryService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findByCode(String code) {
        return queryService.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllByAccountId(Long accountId) {
        return queryService.findAllByAccountId(accountId);
    }
}

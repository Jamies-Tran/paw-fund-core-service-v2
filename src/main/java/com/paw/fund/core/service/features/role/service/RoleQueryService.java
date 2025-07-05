package com.paw.fund.core.service.features.role.service;

import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.features.role.repository.database.IRoleMapper;
import com.paw.fund.core.service.features.role.repository.database.IRoleRepository;
import com.paw.fund.core.service.features.role.repository.database.RoleEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleQueryService {
    IRoleRepository repository;

    IRoleMapper mapper;

    protected Optional<Role> findByCode(String code) {
        return repository.findByRoleCode(code)
                .map(mapper::toDto);
    }

    protected List<Role> findAllByAccountId(Long accountId) {
        List<RoleEntity> roles = repository.findAllByAccountId(accountId);

        return mapper.toDto(roles);
    }
}

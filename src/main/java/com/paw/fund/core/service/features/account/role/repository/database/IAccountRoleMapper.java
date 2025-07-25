package com.paw.fund.core.service.features.account.role.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.account.role.AccountRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountRoleMapper extends IEntityMapper<AccountRoleEntity, AccountRole> {
}

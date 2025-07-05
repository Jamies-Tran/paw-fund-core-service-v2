package com.paw.fund.core.service.features.relation.account.role.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.relation.account.role.AccountRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountRoleMapper extends IEntityMapper<AccountRoleEntity, AccountRole> {
}

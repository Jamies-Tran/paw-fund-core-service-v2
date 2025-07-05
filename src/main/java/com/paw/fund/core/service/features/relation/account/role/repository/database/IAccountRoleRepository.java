package com.paw.fund.core.service.features.relation.account.role.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {
}

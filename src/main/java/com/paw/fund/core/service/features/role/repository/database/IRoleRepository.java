package com.paw.fund.core.service.features.role.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleCode(String roleCode);

    @Query("""
        SELECT r
        FROM RoleEntity r
        INNER JOIN AccountRoleEntity ar ON ar.roleId = r.roleId
        WHERE ar.accountId = :accountId
    """)
    List<RoleEntity> findAllByAccountId(Long accountId);
}

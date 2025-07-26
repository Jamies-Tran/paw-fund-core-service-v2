package com.paw.fund.core.service.features.account.license.repository.database;

import com.paw.fund.core.service.domain.account.license.AccountLicenseCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountLicenseRepository extends JpaRepository<AccountLicenseEntity, Long> {
    @Query("""
        SELECT al
        FROM AccountLicenseEntity al
        WHERE al.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND al.accountLicenseId = :accountLicenseId
    """)
    Optional<AccountLicenseEntity> findById(Long accountLicenseId);

    @Query("""
        SELECT al
        FROM AccountLicenseEntity al
        WHERE (al.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()})
            AND (al.createdAt BETWEEN :#{#criteria.timeRange().get(0)} AND :#{#criteria.timeRange().get(1)})
            AND (:#{#criteria.isAccountIdNull()} = TRUE
                OR al.accountId = :#{#criteria.accountId()})
    """)
    Page<AccountLicenseEntity> findAll(AccountLicenseCriteria criteria, Pageable pageable);
}

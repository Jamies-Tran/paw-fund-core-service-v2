package com.paw.fund.core.service.features.account.license.content.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountLicenseContentRepository extends JpaRepository<AccountLicenseContentEntity, Long> {
    @Query("""
        SELECT alc
        FROM AccountLicenseContentEntity alc
        WHERE alc.statusCode = :#{T(com.paw.fund.core.service.enums.EEnableStatus).ENABLED.getCode()}
            AND alc.accountLicenseId = :accountLicenseId
    """)
    List<AccountLicenseContentEntity> findAllByAccountLicenseId(Long accountLicenseId);
}

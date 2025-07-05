package com.paw.fund.core.service.features.account.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Boolean existsByIdentification(String identification);

    @Query("""
        SELECT 
            COUNT(a) > 0
        FROM AccountEntity a
        INNER JOIN VerificationEntity v ON a.accountId = v.accountId
        WHERE v.expiredAt > CURRENT_TIMESTAMP
            AND v.code = :verificationCode
    """)
    Boolean existsByVerificationCode(String verificationCode);

    Optional<AccountEntity> findByEmail(String email);
}

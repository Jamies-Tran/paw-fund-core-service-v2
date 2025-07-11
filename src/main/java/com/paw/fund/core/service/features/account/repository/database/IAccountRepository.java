package com.paw.fund.core.service.features.account.repository.database;

import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByEmailAndEmailNotNull(String email);

    Boolean existsByPhoneAndPhoneNotNull(String phone);

    Boolean existsByIdentificationAndIdentificationNotNull(String identification);

    @Query("""
        SELECT 
            COUNT(a) > 0
        FROM AccountEntity a
        INNER JOIN VerificationEntity v ON a.accountId = v.accountId
        WHERE v.expiredAt > CURRENT_TIMESTAMP
            AND v.code = :verificationCode
            AND v.typeCode = :#{#verificationType.getCode()}
    """)
    Boolean existsByVerificationCode(String verificationCode, EVerificationType verificationType);

    @Query("""
        SELECT a
        FROM AccountEntity a
        INNER JOIN VerificationEntity v ON v.accountId = a.accountId
        WHERE v.expiredAt > CURRENT_TIMESTAMP
            AND v.code = :verificationCode
            AND v.typeCode = :#{#verificationType.getCode()}
    """)
    Optional<AccountEntity> findByVerificationCode(String verificationCode, EVerificationType verificationType);

    Optional<AccountEntity> findByEmail(String email);

    @Query("""
        SELECT 
            v.dataHolder AS dataHolder,
            a.accountId AS accountId
        FROM AccountEntity a
        INNER JOIN VerificationEntity v ON a.accountId = v.accountId
        WHERE v.code = :verificationCode
            AND v.typeCode = :#{T(com.paw.fund.core.service.domain.verification.enums.EVerificationType).CHANGE_EMAIL.getCode()}
            AND v.expiredAt > CURRENT_TIMESTAMP
    """)
    Optional<AccountVerificationDao> findDataHolderByVerificationCode(String verificationCode);
}

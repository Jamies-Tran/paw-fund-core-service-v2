package com.paw.fund.core.service.features.verification.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVerificationRepository extends JpaRepository<VerificationEntity, Long> {
    @Query("""
        SELECT 
            a.accountId AS accountId,
            a.email AS email
        FROM AccountEntity a
        WHERE a.email = :email
    """)
    Optional<VerificationAccountDao> findVerificationAccountByEmail(String email);

    void deleteByCode(String code);
}

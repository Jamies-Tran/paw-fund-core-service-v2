package com.paw.fund.core.service.features.login.info.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginInfoRepository extends JpaRepository<LoginInfoEntity, Long> {
    @Query("""
        SELECT 
            a.accountId AS accountId,
            a.email AS email,
            a.statusCode AS statusCode
        FROM AccountEntity a
        WHERE a.email = :email
    """)
    Optional<LoginAccountDao> findLoginAccountByEmail(String email);

    Optional<LoginInfoEntity> findTopByAccountIdOrderByCreatedAtDesc(Long accountId);

}

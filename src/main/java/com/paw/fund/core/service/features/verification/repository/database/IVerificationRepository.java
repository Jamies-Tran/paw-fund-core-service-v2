package com.paw.fund.core.service.features.verification.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVerificationRepository extends JpaRepository<VerificationEntity, Long> {
}

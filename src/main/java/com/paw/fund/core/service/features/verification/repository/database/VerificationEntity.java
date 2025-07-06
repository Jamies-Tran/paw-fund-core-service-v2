package com.paw.fund.core.service.features.verification.repository.database;

import com.paw.fund.core.service.bootstrap.config.auditor.Auditor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long verificationId;

    @Column
    Long accountId;

    @Column(unique = true)
    String code;

    @Column
    String dataHolder;

    @Column
    String typeCode;

    @Column
    String typeName;

    @Column
    LocalDateTime expiredAt;
}

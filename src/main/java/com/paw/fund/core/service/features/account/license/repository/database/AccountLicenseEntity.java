package com.paw.fund.core.service.features.account.license.repository.database;

import com.paw.fund.core.service.bootstrap.config.auditor.Auditor;
import com.paw.fund.core.service.domain.media.info.MediaInfoList;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_license")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountLicenseEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountLicenseId;

    @Column
    Long licenseTemplateId;

    @Column
    Long accountId;

    @Column
    String licenseNumber;

    @Column
    LocalDateTime expiryDate;

    @Column
    LocalDateTime issueDate;

    @Column
    String statusCode;

    @Column
    String statusName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    MediaInfoList media;
}

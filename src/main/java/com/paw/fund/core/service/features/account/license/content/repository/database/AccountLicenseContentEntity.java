package com.paw.fund.core.service.features.account.license.content.repository.database;

import com.paw.fund.core.service.bootstrap.config.auditor.Auditor;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
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
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_license_contents")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountLicenseContentEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountLicenseContentId;

    @Column
    Long accountLicenseId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    AccountLicenseContent.LicenseContent content;

    @Column
    String statusCode;

    @Column
    String statusName;
}

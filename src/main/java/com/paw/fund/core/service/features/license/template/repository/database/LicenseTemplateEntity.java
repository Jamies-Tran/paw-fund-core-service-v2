package com.paw.fund.core.service.features.license.template.repository.database;

import com.paw.fund.core.service.bootstrap.config.auditor.Auditor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "license_templates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LicenseTemplateEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long licenseTemplateId;

    @Column
    String title;
    @Column
    String description;

    @Column
    String licenseTypeCode;

    @Column
    String licenseTypeName;

    @Column
    String statusCode;

    @Column
    String statusName;
}

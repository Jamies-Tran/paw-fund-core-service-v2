package com.paw.fund.core.service.features.license.section.repository.database;

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
@Table(name = "license_template_sections")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LicenseTemplateSectionEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long licenseTemplateSectionId;

    @Column
    Long licenseTemplateId;

    @Column
    String sectionTitle;

    @Column
    String statusCode;

    @Column
    String statusName;
}

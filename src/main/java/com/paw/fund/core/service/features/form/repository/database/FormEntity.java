package com.paw.fund.core.service.features.form.repository.database;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forms")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long formId;

    @Column
    Long shelterId;

    @Column
    String title;

    @Column
    String description;

    @Column
    String formTypeCode;

    @Column
    String formTypeName;

    @Column
    String statusCode;

    @Column
    String statusName;
}

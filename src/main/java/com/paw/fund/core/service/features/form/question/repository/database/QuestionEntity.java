package com.paw.fund.core.service.features.form.question.repository.database;

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
@Table(name = "questions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;

    @Column
    Long formId;

    @Column
    String questionText;

    @Column
    String questionTypeCode;

    @Column
    String questionTypeName;

    @Column
    String statusCode;

    @Column
    String statusName;
}

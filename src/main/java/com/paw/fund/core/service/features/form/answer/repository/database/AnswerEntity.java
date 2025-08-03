package com.paw.fund.core.service.features.form.answer.repository.database;

import com.paw.fund.core.service.bootstrap.config.auditor.Auditor;
import com.paw.fund.core.service.domain.form.answer.Answer;
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
@Table(name = "answers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long answerId;

    @Column
    Long formReplyId;

    @Column
    String statusCode;

    @Column
    String statusName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    Answer.AnswerContent answerContent;
}

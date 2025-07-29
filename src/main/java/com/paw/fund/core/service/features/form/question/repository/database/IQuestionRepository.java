package com.paw.fund.core.service.features.form.question.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query("""
        SELECT q
        FROM QuestionEntity q
        WHERE q.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND q.formId = :formId
    """)
    List<QuestionEntity> findAllByFormId(Long formId);
}

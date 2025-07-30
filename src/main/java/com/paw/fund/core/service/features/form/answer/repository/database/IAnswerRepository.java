package com.paw.fund.core.service.features.form.answer.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerRepository extends JpaRepository<AnswerEntity, Long> {
    @Query("""
        SELECT a
        FROM AnswerEntity a
        WHERE a.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND a.formReplyId = :formReplyId
    """)
    List<AnswerEntity> findAllByFormReplyId(Long formReplyId);
}

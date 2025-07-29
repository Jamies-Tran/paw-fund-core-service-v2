package com.paw.fund.core.service.features.form.option.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOptionRepository extends JpaRepository<OptionEntity, Long> {
    @Query("""
        SELECT o
        FROM OptionEntity o
        WHERE o.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND o.questionId IN :questionIds
    """)
    List<OptionEntity> findAllByQuestionIdIn(List<Long> questionIds);

    List<OptionEntity> findAllByQuestionId(Long questionId);
}

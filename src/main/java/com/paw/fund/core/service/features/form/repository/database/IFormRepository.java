package com.paw.fund.core.service.features.form.repository.database;

import com.paw.fund.core.service.domain.form.FormCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFormRepository extends JpaRepository<FormEntity, Long> {
    @Query("""
        SELECT f
        FROM FormEntity f
        WHERE f.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND f.formId = :formId
    """)
    Optional<FormEntity> findById(Long formId);

    @Query("""
        SELECT COUNT(f) > 0
        FROM FormEntity f
        WHERE f.statusCode = :#{T(com.paw.fund.core.service.enums.EEnableStatus).ENABLED.getCode()}
            AND f.formTypeCode = :formTypeCode
     """)
    Boolean existsByFormTypeCode(String formTypeCode);

    @Query("""
        SELECT f
        FROM FormEntity f
        WHERE (f.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()})
            AND (f.createdAt BETWEEN :#{#criteria.timeRange().get(0)}
                AND :#{#criteria.timeRange().get(1)})
            AND (:#{#criteria.search().empty} = TRUE
                OR f.title ILIKE %:#{#criteria.search()}%)
            AND (:#{#criteria.formTypeCodes().empty} = TRUE
                OR f.formTypeCode IN :#{#criteria.formTypeCodes()})
            AND (:#{#criteria.statusCodes().empty} = TRUE
                OR f.statusCode IN :#{#criteria.statusCodes()})
    """)
    Page<FormEntity> findAll(FormCriteria criteria, PageRequest pageRequest);
}

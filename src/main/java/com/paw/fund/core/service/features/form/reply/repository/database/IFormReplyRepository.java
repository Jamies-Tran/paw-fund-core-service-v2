package com.paw.fund.core.service.features.form.reply.repository.database;

import com.paw.fund.core.service.domain.form.FormCriteria;
import com.paw.fund.core.service.domain.form.reply.FormReplyCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFormReplyRepository extends JpaRepository<FormReplyEntity, Long> {
    @Query("""
        SELECT fr
        FROM FormReplyEntity fr
        WHERE fr.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND fr.formReplyId = :formReplyId
    """)
    Optional<FormReplyEntity> findById(Long formReplyId);

    @Query("""
        SELECT fr
        FROM FormReplyEntity fr
        WHERE (fr.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()})
            AND (fr.createdAt BETWEEN :#{#criteria.timeRange().get(0)} 
                AND :#{#criteria.timeRange().get(1)})
            AND (:#{#criteria.search().empty} = TRUE
                OR fr.title ILIKE %:#{#criteria.search()}%)
            AND (:#{#criteria.statusCodes().empty} = TRUE
                OR fr.statusCode IN :#{#criteria.statusCodes()})
            AND (:#{#criteria.isAccountIdNull()} = TRUE
                OR fr.accountId = :#{#criteria.accountId()})
                    
    """)
    Page<FormReplyEntity> findAll(FormReplyCriteria criteria, Pageable pageable);
}

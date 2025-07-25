package com.paw.fund.core.service.features.license.template.repository.database;

import com.paw.fund.core.service.domain.license.template.LicenseTemplateCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILicenseTemplateRepository extends JpaRepository<LicenseTemplateEntity, Long> {
    @Query("""
        SELECT lt
        FROM LicenseTemplateEntity lt
        WHERE lt.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND lt.licenseTemplateId = :licenseTemplateId
    """)
    Optional<LicenseTemplateEntity> findById(Long licenseTemplateId);

    @Query("""
        SELECT lt
        FROM LicenseTemplateEntity lt
        WHERE lt.statusCode != :#{T(com.paw.fund.core.service.enums.EDeleteStatus).DELETED.getCode()}
            AND (lt.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)} 
                AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.search().empty} = TRUE
                OR lt.title ILIKE %:#{#searchCriteria.search()}%)
            AND (:#{#searchCriteria.statusCodes().empty} = TRUE
                OR lt.statusCode IN :#{#searchCriteria.statusCodes()})
            AND (:#{#searchCriteria.licenseTypeCodes().empty} = TRUE
                OR lt.licenseTypeCode IN :#{#searchCriteria.licenseTypeCodes()})
            
    """)
    Page<LicenseTemplateEntity> findAll(LicenseTemplateCriteria searchCriteria, Pageable pageable);
}

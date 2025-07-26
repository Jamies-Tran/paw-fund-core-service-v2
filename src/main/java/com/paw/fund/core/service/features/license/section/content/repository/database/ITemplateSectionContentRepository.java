package com.paw.fund.core.service.features.license.section.content.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITemplateSectionContentRepository extends JpaRepository<TemplateSectionContentEntity, Long> {
    @Query("""
        SELECT tsc
        FROM TemplateSectionContentEntity tsc
        WHERE tsc.statusCode = :#{T(com.paw.fund.core.service.enums.EEnableStatus).ENABLED.getCode()}
            AND tsc.licenseTemplateSectionId IN :ids
    """)
    List<TemplateSectionContentEntity> findAllByLicenseTemplateSectionIdIn(List<Long> ids);

    List<TemplateSectionContentEntity> findAllByLicenseTemplateSectionId(Long id);
}

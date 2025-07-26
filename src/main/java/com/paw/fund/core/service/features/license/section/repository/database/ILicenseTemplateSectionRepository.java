package com.paw.fund.core.service.features.license.section.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILicenseTemplateSectionRepository extends JpaRepository<LicenseTemplateSectionEntity, Long> {
    @Query("""
        SELECT ltc
        FROM LicenseTemplateSectionEntity ltc
        WHERE ltc.statusCode = :#{T(com.paw.fund.core.service.enums.EEnableStatus).ENABLED.getCode()}
            AND ltc.licenseTemplateId = :licenseTemplateId
    """)
    List<LicenseTemplateSectionEntity> findAllByLicenseTemplateId(Long licenseTemplateId);
}

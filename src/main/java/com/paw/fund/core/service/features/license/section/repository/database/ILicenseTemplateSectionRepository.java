package com.paw.fund.core.service.features.license.section.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILicenseTemplateSectionRepository extends JpaRepository<LicenseTemplateSectionEntity, Long> {
    List<LicenseTemplateSectionEntity> findAllByLicenseTemplateId(Long licenseTemplateId);

    void deleteAllByLicenseTemplateId(Long id);
}

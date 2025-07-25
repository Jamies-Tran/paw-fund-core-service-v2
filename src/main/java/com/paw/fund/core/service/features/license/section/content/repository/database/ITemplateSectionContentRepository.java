package com.paw.fund.core.service.features.license.section.content.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITemplateSectionContentRepository extends JpaRepository<TemplateSectionContentEntity, Long> {
    List<TemplateSectionContentEntity> findAllByLicenseTemplateSectionIdIn(List<Long> ids);

    void deleteAllByLicenseTemplateSectionIdIn(List<Long> ids);
}

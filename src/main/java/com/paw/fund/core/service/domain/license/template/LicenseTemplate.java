package com.paw.fund.core.service.domain.license.template;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.enums.license.template.ELicenseTemplateStatus;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record LicenseTemplate(
        Long licenseTemplateId,

        String title,
        String description,

                
        String licenseTypeCode,
        String licenseTypeName,

                
        String statusCode,
        String statusName,

        @With List<LicenseTemplateSection> sections
) {
    public LicenseTemplate {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = ELicenseTemplateStatus.ENABLED.getCode();
            statusName = ELicenseTemplateStatus.ENABLED.getName();
        }
    }
}

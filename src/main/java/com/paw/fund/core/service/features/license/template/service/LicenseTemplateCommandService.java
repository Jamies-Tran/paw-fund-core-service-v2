package com.paw.fund.core.service.features.license.template.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotValid;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.enums.EDeleteStatus;
import com.paw.fund.core.service.enums.license.template.ELicenseTemplateStatus;
import com.paw.fund.core.service.features.license.template.repository.database.ILicenseTemplateMapper;
import com.paw.fund.core.service.features.license.template.repository.database.ILicenseTemplateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateCommandService {
    ILicenseTemplateRepository repository;

    ILicenseTemplateMapper mapper;

    protected Long save(LicenseTemplate licenseTemplate) {
        if (repository.existsByLicenseTypeCode(licenseTemplate.licenseTypeCode())) {
            throw new PResourceNotValid("Giấy phép %s đã tồn tại".formatted(licenseTemplate.licenseTypeName()));
        }

        return repository.save(mapper.toEntity(licenseTemplate)).getLicenseTemplateId();
    }

    protected void update(Long licenseTemplateId, LicenseTemplate licenseTemplate) {
        repository.findById(licenseTemplateId)
                .ifPresentOrElse(
                        license -> {
                            mapper.update(license, licenseTemplate);
                            repository.save(license);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }

    protected void updateStatus(Long licenseTemplateId, ELicenseTemplateStatus status) {
        repository.findById(licenseTemplateId)
                .ifPresentOrElse(
                        template -> {
                            template.setStatusCode(status.getCode());
                            template.setStatusName(status.getName());
                            repository.save(template);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }

    protected void delete(Long licenseTemplateId) {
        repository.findById(licenseTemplateId)
                .ifPresentOrElse(
                        template -> {
                            template.setStatusCode(EDeleteStatus.DELETED.getCode());
                            template.setStatusName(EDeleteStatus.DELETED.getName());
                            repository.save(template);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }
}

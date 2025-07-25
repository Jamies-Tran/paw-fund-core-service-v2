package com.paw.fund.core.service.features.license.template.controller;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.license.template.ILicenseTemplateUseCase;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.template.ILicenseTemplateRequestMapper;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.template.ILicenseTemplateResponseMapper;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateRequest;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplateController implements ILicenseTemplateApi{
    ILicenseTemplateUseCase licenseTemplateUseCase;

    ILicenseTemplateResponseMapper responseMapper;

    ILicenseTemplateRequestMapper requestMapper;

    @Override
    public PValueResponse<LicenseTemplateResponse> findById(Long licenseTemplateId) {

        return PValueResponse.success(licenseTemplateUseCase.findById(licenseTemplateId)
                .map(responseMapper::toModel)
                .orElseThrow(PResourceNotFoundException::new));
    }

    @Override
    public PValueResponse<?> update(Long licenseTemplateId, LicenseTemplateRequest request) {
        licenseTemplateUseCase.update(licenseTemplateId, requestMapper.toDto(request));

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> enable(Long licenseTemplateId) {
        licenseTemplateUseCase.enable(licenseTemplateId);

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> disable(Long licenseTemplateId) {
        licenseTemplateUseCase.disable(licenseTemplateId);

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> delete(Long licenseTemplateId) {
        licenseTemplateUseCase.delete(licenseTemplateId);

        return PValueResponse.successNoData();
    }
}

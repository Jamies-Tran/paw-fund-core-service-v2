package com.paw.fund.core.service.features.license.template.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PPageResponse;
import com.paw.fund.core.service.bootstrap.config.rest.PSorter;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.license.template.ILicenseTemplateUseCase;
import com.paw.fund.core.service.domain.license.template.LicenseTemplateCriteria;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.template.ILicenseTemplateResponseMapper;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateRequest;
import com.paw.fund.core.service.features.license.template.controller.models.mappers.template.ILicenseTemplateRequestMapper;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTemplatesController implements ILicenseTemplatesApi {
    ILicenseTemplateUseCase licenseTemplateUseCase;

    ILicenseTemplateRequestMapper requestMapper;

    ILicenseTemplateResponseMapper responseMapper;

    @Override
    public PValueResponse<Long> save(LicenseTemplateRequest request) {

        return PValueResponse.success(licenseTemplateUseCase.save(requestMapper.toDto(request)));
    }

    @Override
    public PPageResponse<LicenseTemplateResponse> findAll(
            String search,
            List<LocalDateTime> timeRange,
            List<String> statusCodes,
            List<String> templateTypeCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        LicenseTemplateCriteria criteria = LicenseTemplateCriteria.of(
                search,
                timeRange,
                statusCodes,
                templateTypeCodes
        );
        PageRequest pageRequest = PageRequest.of(current, pageSize, PSorter.of(sorter));
        Page<LicenseTemplateResponse> responses = licenseTemplateUseCase.findAll(criteria, pageRequest)
                .map(responseMapper::toModel);

        return PPageResponse.success(responses);
    }
}

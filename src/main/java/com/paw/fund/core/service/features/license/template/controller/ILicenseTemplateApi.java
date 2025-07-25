package com.paw.fund.core.service.features.license.template.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateRequest;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/license-template/{licenseTemplateId}")
@Tag(name = "License", description = "QL giấy phép")
public interface ILicenseTemplateApi {
    @GetMapping
    PValueResponse<LicenseTemplateResponse> findById(@PathVariable Long licenseTemplateId);

    @PutMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    PValueResponse<?> update(@PathVariable Long licenseTemplateId,
                             @RequestBody @Valid LicenseTemplateRequest request);

    @PatchMapping("/enable") @PreAuthorize("hasRole('ROLE_ADMIN')")
    PValueResponse<?> enable(@PathVariable Long licenseTemplateId);

    @PatchMapping("/disable") @PreAuthorize("hasRole('ROLE_ADMIN')")
    PValueResponse<?> disable(@PathVariable Long licenseTemplateId);

    @DeleteMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    PValueResponse<?> delete(@PathVariable Long licenseTemplateId);
}

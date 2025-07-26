package com.paw.fund.core.service.features.license.template.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PPageResponse;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateRequest;
import com.paw.fund.core.service.features.license.template.controller.models.template.LicenseTemplateResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/license-template")
@Tag(name = "License", description = "QL giấy phép")
public interface ILicenseTemplatesApi {
    @PostMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    PValueResponse<Long> save(@RequestBody @Valid LicenseTemplateRequest request);

    @GetMapping("/all")
    PPageResponse<LicenseTemplateResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "") List<String> statusCodes,
            @RequestParam(required = false, value = "templateTypeCodes", defaultValue = "") List<String> templateTypeCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt_desc") String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25") Integer pageSize
    );
}

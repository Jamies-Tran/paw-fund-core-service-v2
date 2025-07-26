package com.paw.fund.core.service.features.account.license.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseDetailResponse;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseRequest;
import com.paw.fund.core.service.features.account.license.controller.models.AccountLicenseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/account-license/{accountLicenseId}")
@Tag(name = "Account License", description = "QL giấy phép")
public interface IAccountLicenseApi {
    @GetMapping
    PValueResponse<AccountLicenseDetailResponse> findById(@PathVariable Long accountLicenseId);

    @PutMapping @PreAuthorize("hasRole('ROLE_SHELTER_OWNER')")
    PValueResponse<?> update(@PathVariable Long accountLicenseId, @RequestBody @Valid AccountLicenseRequest request);

    @DeleteMapping @PreAuthorize("hasRole('ROLE_SHELTER_OWNER')")
    PValueResponse<?> delete(@PathVariable Long accountLicenseId);
}

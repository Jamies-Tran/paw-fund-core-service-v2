package com.paw.fund.core.service.features.login.info.controller.models.login.account;

import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.features.account.controller.models.role.RoleResponse;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record LoginAccountResponse(
        Long accountId,
        String email,
        String statusCode,
        List<RoleResponse> roles
) {
}

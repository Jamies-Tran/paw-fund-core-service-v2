package com.paw.fund.core.service.features.account.controller.models.role;

import lombok.Builder;

@Builder
public record RoleResponse(
        Long roleId,
        String roleCode,
        String roleName
) {
}

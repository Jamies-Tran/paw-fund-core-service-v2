package com.paw.fund.core.service.domain.role;

import lombok.Builder;

@Builder
public record Role(
        Long roleId,
        String roleCode,
        String roleName
) {
}

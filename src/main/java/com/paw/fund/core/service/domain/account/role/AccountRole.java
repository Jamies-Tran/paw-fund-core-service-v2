package com.paw.fund.core.service.domain.account.role;

import lombok.Builder;

@Builder
public record AccountRole(
        Long accountRoleId,
        Long accountId,
        Long roleId
) {
}

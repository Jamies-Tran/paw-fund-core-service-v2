package com.paw.fund.core.service.domain.relation.account.role;

import lombok.Builder;

@Builder
public record AccountRole(
        Long accountRoleId,
        Long accountId,
        Long roleId
) {
}

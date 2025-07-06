package com.paw.fund.core.service.domain.login.info;

import com.paw.fund.core.service.domain.role.Role;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record LoginAccount(
        Long accountId,
        String email,
        String password,
        String statusCode,
        @With List<Role> roles
) {
}

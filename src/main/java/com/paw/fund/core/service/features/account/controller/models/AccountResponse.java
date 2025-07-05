package com.paw.fund.core.service.features.account.controller.models;

import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.features.account.controller.models.media.MediaResponse;
import com.paw.fund.core.service.features.account.controller.models.role.RoleResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record AccountResponse(
        Long accountId,
        String avatar,
        String firstName,
        String lastName,
        String identification,

        String email,
        String password,
        String phone,
        String address,

        LocalDate dateOfBirth,
        String genderCode,
        String genderName,

        List<RoleResponse> roles,
        List<MediaResponse> medias,

        String statusCode,
        String statusName
) {
}

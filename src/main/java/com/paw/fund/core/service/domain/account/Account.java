package com.paw.fund.core.service.domain.account;

import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.domain.role.Role;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.List;

@Builder
public record Account(
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

        @With List<Role> roles,
        @With List<Media> medias,

        String statusCode,
        String statusName
) {
}

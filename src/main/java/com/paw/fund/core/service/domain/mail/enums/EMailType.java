package com.paw.fund.core.service.domain.mail.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EMailType {
    VALIDATE_ACCOUNT("VALIDATE_ACCOUNT");

    String code;
}

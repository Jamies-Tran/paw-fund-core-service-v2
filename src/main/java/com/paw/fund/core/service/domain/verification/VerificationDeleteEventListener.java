package com.paw.fund.core.service.domain.verification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationDeleteEventListener extends ApplicationEvent {
    String verificationCode;

    public VerificationDeleteEventListener(Object source, String verificationCode) {
        super(source);
        this.verificationCode = verificationCode;
    }
}

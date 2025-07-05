package com.paw.fund.core.service.domain.verification;

import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationEventListener extends ApplicationEvent {
    String email;
    EVerificationType verificationType;

    public VerificationEventListener(Object source, String email, EVerificationType verificationType) {
        super(source);
        this.email = email;
        this.verificationType = verificationType;
    }
}

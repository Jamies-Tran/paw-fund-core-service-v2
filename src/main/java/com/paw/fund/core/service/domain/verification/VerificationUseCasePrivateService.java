package com.paw.fund.core.service.domain.verification;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotValid;
import com.paw.fund.core.service.domain.mail.enums.EMailType;
import com.paw.fund.core.service.domain.verification.enums.EVerificationType;

import java.util.Random;

public abstract class VerificationUseCasePrivateService {
    protected String generateVerificationCode() {
        Random random = new Random();
        int generatedCode = 100000 + random.nextInt(900000);
        return String.valueOf(generatedCode);
    }

    protected EMailType getMailType(EVerificationType verificationType) {
        switch (verificationType) {
            case ACCOUNT_CREATION -> {
                return EMailType.VALIDATE_ACCOUNT;
            }
            case CHANGE_PASSWORD -> {
                return EMailType.CHANGE_PASSWORD;
            }
            case CHANGE_EMAIL -> {
                return EMailType.CHANGE_EMAIL;
            }
            default -> throw  new PResourceNotValid();
        }
    }
}

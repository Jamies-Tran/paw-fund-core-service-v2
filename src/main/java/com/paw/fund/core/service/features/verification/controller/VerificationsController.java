package com.paw.fund.core.service.features.verification.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.verification.IVerificationUseCase;
import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import com.paw.fund.core.service.features.account.controller.models.UpdateStatusRequest;
import com.paw.fund.core.service.features.verification.controller.models.EmailRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationsController implements IVerificationsApi {
    IVerificationUseCase useCase;

    @Override
    public PValueResponse<?> sendVerification(EmailRequest emailRequest) {
        useCase.save(emailRequest.email(), EVerificationType.ACCOUNT_CREATION);

        return PValueResponse.successNoData();
    }
}

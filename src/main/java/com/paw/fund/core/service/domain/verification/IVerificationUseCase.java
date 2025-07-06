package com.paw.fund.core.service.domain.verification;

import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import lombok.NonNull;

public interface IVerificationUseCase {
    void save(@NonNull String email, @NonNull EVerificationType verificationType);

    void save(@NonNull String email,@NonNull String newEmail, @NonNull EVerificationType verificationType);

    void delete(@NonNull VerificationDeleteEventListener verificationDeleteEventListener);
}

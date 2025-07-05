package com.paw.fund.core.service.features.verification.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import com.paw.fund.core.service.domain.mail.MailEventListener;
import com.paw.fund.core.service.domain.mail.enums.EMailType;
import com.paw.fund.core.service.domain.verification.IVerificationUseCase;
import com.paw.fund.core.service.domain.verification.Verification;
import com.paw.fund.core.service.domain.verification.VerificationEventListener;
import com.paw.fund.core.service.domain.verification.VerificationPrivateService;
import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class VerificationUseCaseService extends VerificationPrivateService implements IVerificationUseCase{
    VerificationCommandService commandService;

    ApplicationEventPublisher publisher;

    IAccountUseCase accountUseCase;

    @NonFinal @Value("${paw.verification.duration}")
    Integer verificationDuration;

    @NonFinal @Value("${app.mail.username}")
    String systemMail;

    @Override
    @Transactional
    public void save(@NonNull String email,@NonNull EVerificationType verificationType) {
        Account account = accountUseCase.findByEmail(email)
                .orElseThrow(PResourceNotFoundException::new);
        String code = generateVerificationCode();
        Verification verification = Verification.builder()
                .accountId(account.accountId())
                .code(code)
                .typeCode(verificationType.getCode())
                .typeName(verificationType.getName())
                .isUsed(false)
                .expiredAt(LocalDateTime.now().plusMinutes(verificationDuration))
                .build();
        commandService.save(verification);
        MailEventListener mailEventListener = new MailEventListener(
                this,
                systemMail,
                account.email(),
                code,
                true,
                EMailType.VALIDATE_ACCOUNT);
        publisher.publishEvent(mailEventListener);
    }

    @Override
    protected String generateVerificationCode() {
        Random random = new Random();
        int generatedCode = 100000 + random.nextInt(900000);
        return String.valueOf(generatedCode);
    }
}

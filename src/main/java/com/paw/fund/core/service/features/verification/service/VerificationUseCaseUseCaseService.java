package com.paw.fund.core.service.features.verification.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.mail.MailEventListener;
import com.paw.fund.core.service.domain.verification.IVerificationUseCase;
import com.paw.fund.core.service.domain.verification.Verification;
import com.paw.fund.core.service.domain.verification.VerificationAccount;
import com.paw.fund.core.service.domain.verification.VerificationDeleteEventListener;
import com.paw.fund.core.service.domain.verification.VerificationUseCasePrivateService;
import com.paw.fund.core.service.domain.verification.enums.EVerificationType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class VerificationUseCaseUseCaseService
        extends VerificationUseCasePrivateService implements IVerificationUseCase {
    VerificationCommandService commandService;

    VerificationQueryService queryService;

    ApplicationEventPublisher publisher;

    @NonFinal @Value("${paw.verification.duration}")
    Integer verificationDuration;

    @NonFinal @Value("${app.mail.username}")
    String systemMail;

    @Override
    @Transactional
    @Retryable(
            retryFor = DataIntegrityViolationException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000, multiplier = 2)
    )
    public void save(@NonNull String email, @NonNull EVerificationType verificationType) {
        VerificationAccount account = queryService.findVerificationAccountByEmail(email)
                .orElseThrow(PResourceNotFoundException::new);
        String code = generateVerificationCode();
        Verification verification = Verification.builder()
                .accountId(account.accountId())
                .code(code)
                .typeCode(verificationType.getCode())
                .typeName(verificationType.getName())
                .expiredAt(LocalDateTime.now().plusMinutes(verificationDuration))
                .build();
        commandService.save(verification);
        MailEventListener mailEventListener = new MailEventListener(
                this,
                systemMail,
                account.email(),
                code,
                true,
                getMailType(verificationType));
        publisher.publishEvent(mailEventListener);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = DataIntegrityViolationException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000, multiplier = 2)
    )
    public void save(@NonNull String email, @NonNull String newEmail, @NonNull EVerificationType verificationType) {
        VerificationAccount account = queryService.findVerificationAccountByEmail(email)
                .orElseThrow(PResourceNotFoundException::new);
        String code = generateVerificationCode();
        Verification verification = Verification.builder()
                .accountId(account.accountId())
                .code(code)
                .dataHolder(newEmail)
                .typeCode(verificationType.getCode())
                .typeName(verificationType.getName())
                .expiredAt(LocalDateTime.now().plusMinutes(verificationDuration))
                .build();
        commandService.save(verification);
        MailEventListener mailEventListener = new MailEventListener(
                this,
                systemMail,
                newEmail,
                code,
                true,
                getMailType(verificationType));
        publisher.publishEvent(mailEventListener);
    }

    @Override
    @Transactional
    @EventListener
    public void delete(@NonNull VerificationDeleteEventListener eventListener) {
        commandService.delete(eventListener.getVerificationCode());
    }
}

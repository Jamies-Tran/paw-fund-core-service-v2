package com.paw.fund.core.service.features.mail.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotValid;
import com.paw.fund.core.service.domain.mail.IMailUseCase;
import com.paw.fund.core.service.domain.mail.MailEventListener;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailUseCaseService implements IMailUseCase {
    JavaMailSender javaMailSender;

    @Override
    @EventListener
    public void sendMail(@NonNull MailEventListener eventListener) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            MailEventListener validateAccount;
            switch (eventListener.getMailType()) {
                case VALIDATE_ACCOUNT -> validateAccount = eventListener.validateAccount();
                case CHANGE_PASSWORD -> validateAccount = eventListener.changePassword();
                case CHANGE_EMAIL -> validateAccount = eventListener.changeEmail();
                default -> throw new PResourceNotValid();
            }
            helper.setFrom(new InternetAddress(validateAccount.getFrom(), "Paw Fund"));
            helper.setSubject(validateAccount.getSubject());
            helper.setTo(validateAccount.getTo());
            helper.setText(validateAccount.getBody(), true);
            helper.setSentDate(new Date());
            javaMailSender.send(mimeMessage);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

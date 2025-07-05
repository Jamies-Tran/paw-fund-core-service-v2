package com.paw.fund.core.service.domain.mail;

public interface IMailUseCase {
    void sendMail(MailEventListener eventListener);
}

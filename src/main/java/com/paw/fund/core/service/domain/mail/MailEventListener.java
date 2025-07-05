package com.paw.fund.core.service.domain.mail;

import com.paw.fund.core.service.domain.mail.enums.EMailType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailEventListener extends ApplicationEvent {
    String from;
    String to;
    String content;
    Boolean isHTMLSupport;

    EMailType mailType;

    @Setter @NonFinal String subject;
    @Setter @NonFinal  String body;

    public MailEventListener(Object source,
                             String from,
                             String to,
                             String content,
                             Boolean isHTMLSupport,
                             EMailType mailType) {
        super(source);
        this.from = from;
        this.to = to;
        this.content = content;
        this.isHTMLSupport = isHTMLSupport;
        this.mailType = mailType;
    }

    public MailEventListener validateAccount() {
        String subject = "[PAWFUND] - Mã xác nhận tài khoản";
        String body = "<html><body><h2>Xác nhận tài khoản của bạn</h2><p>Chào bạn,</p><p>Mã xác nhận của bạn là:</p><h1 style='color: #007bff;'> %s </h1><p>Mã này có hiệu lực trong 15 phút.</p><p>Nếu bạn không yêu cầu, vui lòng bỏ qua email này.</p><br><p>Trân trọng,<br><strong>PawFund</strong></p></body></html>"
                .formatted(this.getContent());

        this.setSubject(subject);
        this.setBody(body);

        return this;
    }
}

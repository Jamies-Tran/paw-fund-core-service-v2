package com.paw.fund.core.service.domain.form.reply;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.form.answer.Answer;
import com.paw.fund.core.service.enums.EEnableStatus;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record FormReply(
        Long formReplyId,
        @With Long accountId,
        String title,
        String description,
        String statusCode,
        String statusName,
        @With List<Answer> answers
) {
    public FormReply {
        if (PObjectUtils.isEmpty(statusCode)) {
            statusCode = EEnableStatus.ENABLED.getCode();
            statusName = EEnableStatus.ENABLED.getName();
        }
    }
}

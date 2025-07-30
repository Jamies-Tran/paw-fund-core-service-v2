package com.paw.fund.core.service.features.form.reply.controller;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.form.reply.IFormReplyUseCase;
import com.paw.fund.core.service.features.form.controller.models.IFormRequestMapper;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyDetailResponse;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyRequest;
import com.paw.fund.core.service.features.form.reply.controller.models.mappers.IFormReplyDetailResponseMapper;
import com.paw.fund.core.service.features.form.reply.controller.models.mappers.IFormReplyRequestMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormReplyController implements IFormReplyApi {
    IFormReplyUseCase replyUseCase;

    IFormReplyRequestMapper requestMapper;

    IFormReplyDetailResponseMapper responseMapper;

    @Override
    public PValueResponse<FormReplyDetailResponse> findById(Long formReplyId) {
        return PValueResponse.success(replyUseCase
                        .findById(formReplyId)
                        .map(responseMapper::toModel)
                        .orElseThrow(PResourceNotFoundException::new));
    }

    @Override
    public PValueResponse<?> update(Long formReplyId, FormReplyRequest request) {
        replyUseCase.update(formReplyId, requestMapper.toDto(request));

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> delete(Long formReplyId) {
        replyUseCase.delete(formReplyId);

        return PValueResponse.successNoData();
    }
}

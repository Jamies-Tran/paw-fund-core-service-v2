package com.paw.fund.core.service.features.form.reply.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PPageResponse;
import com.paw.fund.core.service.bootstrap.config.rest.PSorter;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.form.reply.FormReplyCriteria;
import com.paw.fund.core.service.domain.form.reply.IFormReplyUseCase;
import com.paw.fund.core.service.features.form.controller.models.IFormRequestMapper;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyRequest;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyDetailResponse;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyResponse;
import com.paw.fund.core.service.features.form.reply.controller.models.mappers.IFormReplyRequestMapper;
import com.paw.fund.core.service.features.form.reply.controller.models.mappers.IFormReplyResponseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormRepliesController implements IFormRepliesApi {
    IFormReplyUseCase replyUseCase;

    IFormReplyRequestMapper requestMapper;

    IFormReplyResponseMapper responseMapper;

    @Override
    public PValueResponse<Long> save(FormReplyRequest request) {
        return PValueResponse.success(replyUseCase.save(requestMapper.toDto(request)));
    }

    @Override
    public PPageResponse<FormReplyResponse> findAll(
            String search,
            Long accountId,
            List<LocalDateTime> timeRange,
            List<String> statusCodes,
            String sorter, Integer current, Integer pageSize
    ) {
        FormReplyCriteria criteria = FormReplyCriteria.of(search, accountId, timeRange, statusCodes);
        PageRequest pageRequest = PageRequest.of(current, pageSize, PSorter.of(sorter));
        Page<FormReplyResponse> responses = replyUseCase.findAll(criteria, pageRequest)
                .map(responseMapper::toModel);

        return PPageResponse.success(responses);
    }
}

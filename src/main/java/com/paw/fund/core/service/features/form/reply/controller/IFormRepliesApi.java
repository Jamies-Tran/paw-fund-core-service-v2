package com.paw.fund.core.service.features.form.reply.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PPageResponse;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyRequest;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyDetailResponse;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/form-reply")
@Tag(name = "Form Reply", description = "QL form phản hội")
public interface IFormRepliesApi {
    @PostMapping
    PValueResponse<Long> save(@RequestBody @Valid FormReplyRequest request);

    @GetMapping("/all")
    PPageResponse<FormReplyResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "accountId", defaultValue = "") Long accountId,
            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "") List<String> statusCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt_desc") String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25") Integer pageSize
    );
}

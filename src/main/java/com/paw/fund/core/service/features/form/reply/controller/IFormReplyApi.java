package com.paw.fund.core.service.features.form.reply.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyRequest;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyDetailResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form-reply/{formReplyId}")
@Tag(name = "Form Reply", description = "QL form phản hội")
public interface IFormReplyApi {
    @GetMapping
    PValueResponse<FormReplyDetailResponse> findById(@PathVariable Long formReplyId);

    @PutMapping
    PValueResponse<?> update(@PathVariable Long formReplyId, @RequestBody @Valid FormReplyRequest request);

    @DeleteMapping
    PValueResponse<?> delete(@PathVariable Long formReplyId);
}

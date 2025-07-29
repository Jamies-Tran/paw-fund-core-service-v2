package com.paw.fund.core.service.features.form.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.form.controller.models.FormRequest;
import com.paw.fund.core.service.features.form.controller.models.FormResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form/{formId}")
@Tag(name = "Form", description = "QL form khảo sát")
public interface IFormApi {
    @GetMapping
    PValueResponse<FormResponse> findById(@PathVariable Long formId);

    @PutMapping
    PValueResponse<?> update(@PathVariable Long formId, @RequestBody @Valid FormRequest request);

    @PatchMapping("/enable")
    PValueResponse<?> enable(@PathVariable Long formId);

    @PatchMapping("/disable")
    PValueResponse<?> disable(@PathVariable Long formId);

    @DeleteMapping
    PValueResponse<?> delete(@PathVariable Long formId);
}

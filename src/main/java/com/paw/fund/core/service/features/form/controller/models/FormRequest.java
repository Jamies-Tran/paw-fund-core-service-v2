package com.paw.fund.core.service.features.form.controller.models;

import com.paw.fund.core.service.features.form.controller.models.question.QuestionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record FormRequest(
        @NotNull(message = "Vui lòng nhập tiêu đề của form")
        String title,
        String description,
        @NotNull(message = "Vui lòng chọn loại form")
        String formTypeCode,
        @NotNull(message = "Vui lòng chọn loại form")
        String formTypeName,
        @Valid
        List<QuestionRequest> questions
) {
}

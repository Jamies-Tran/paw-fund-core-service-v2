package com.paw.fund.core.service.features.form.controller.models.question;

import com.paw.fund.core.service.features.form.controller.models.option.OptionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.With;

import java.util.List;

public record QuestionRequest(
        @NotNull(message = "Vui lòng nhập nội dung câu hỏi")
        String questionText,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        String questionTypeCode,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        String questionTypeName,
        @Valid
        List<OptionRequest> options
) {
}

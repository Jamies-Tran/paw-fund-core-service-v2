package com.paw.fund.core.service.features.form.controller;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.form.IFormUseCase;
import com.paw.fund.core.service.features.form.controller.models.FormRequest;
import com.paw.fund.core.service.features.form.controller.models.FormResponse;
import com.paw.fund.core.service.features.form.controller.models.IFormRequestMapper;
import com.paw.fund.core.service.features.form.controller.models.IFormResponseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormController implements IFormApi {
    IFormUseCase formUseCase;

    IFormRequestMapper requestMapper;

    IFormResponseMapper responseMapper;

    @Override
    public PValueResponse<FormResponse> findById(Long formId) {

        return PValueResponse.success(formUseCase.findById(formId)
                .map(responseMapper::toModel)
                .orElseThrow(PResourceNotFoundException::new));
    }

    @Override
    public PValueResponse<?> update(Long formId, FormRequest request) {
        formUseCase.update(formId, requestMapper.toDto(request));

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> enable(Long formId) {
        formUseCase.enable(formId);

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> disable(Long formId) {
        formUseCase.disable(formId);

        return PValueResponse.successNoData();
    }

    @Override
    public PValueResponse<?> delete(Long formId) {
        formUseCase.delete(formId);

        return PValueResponse.successNoData();
    }
}

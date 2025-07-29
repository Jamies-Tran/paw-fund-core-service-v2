package com.paw.fund.core.service.features.form.controller;

import com.paw.fund.core.service.bootstrap.config.rest.PPageResponse;
import com.paw.fund.core.service.bootstrap.config.rest.PSorter;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.domain.form.FormCriteria;
import com.paw.fund.core.service.domain.form.IFormUseCase;
import com.paw.fund.core.service.features.form.controller.models.FormRequest;
import com.paw.fund.core.service.features.form.controller.models.FormResponse;
import com.paw.fund.core.service.features.form.controller.models.IFormRequestMapper;
import com.paw.fund.core.service.features.form.controller.models.IFormResponseMapper;
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
public class FormsController implements IFormsApi {
    IFormUseCase formUseCase;

    IFormRequestMapper requestMapper;

    IFormResponseMapper responseMapper;

    @Override
    public PValueResponse<Long> save(FormRequest request) {
        return PValueResponse.success(formUseCase.save(requestMapper.toDto(request)));
    }

    @Override
    public PPageResponse<FormResponse> findAll(
            String search,
            List<LocalDateTime> timeRange,
            List<String> formTypeCodes,
            List<String> statusCodes,
            String sorter, Integer current, Integer pageSize
    ) {
        FormCriteria criteria = FormCriteria.of(
                search,
                timeRange,
                formTypeCodes,
                statusCodes
        );
        PageRequest pageRequest = PageRequest.of(current, pageSize, PSorter.of(sorter));
        Page<FormResponse> responses = formUseCase.findAll(criteria, pageRequest)
                .map(responseMapper::toModel);

        return PPageResponse.success(responses);
    }
}

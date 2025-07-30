package com.paw.fund.core.service.features.form.reply.controller.models.mappers;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.form.reply.FormReply;
import com.paw.fund.core.service.features.form.reply.controller.models.FormReplyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IFormReplyResponseMapper extends IModelMapper<FormReplyResponse, FormReply> {
}

package com.paw.fund.core.service.features.account.controller.models.mapper;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.features.account.controller.models.media.MediaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IMediaResponseModelMapper extends IModelMapper<MediaResponse, Media> {
}

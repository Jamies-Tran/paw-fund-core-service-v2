package com.paw.fund.core.service.features.media.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.domain.media.enums.EMimeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IMediaMapper extends IEntityMapper<MediaEntity, Media> {
    @Mapping(expression = "java(setMimeTypeCode(dto.url()))", target = "mediaTypeCode")
    @Mapping(expression = "java(setMimeTypeName(dto.url()))", target = "mediaTypeName")
    MediaEntity toEntity(Media dto);

    @Named("setMimeTypeCode")
    default String setMimeTypeCode(String url) {
        return EMimeType.INSTANCE.findMimeType(url).getCode();
    }

    @Named("setMimeTypeName")
    default String setMimeTypeName(String url) {
        return EMimeType.INSTANCE.findMimeType(url).getName();
    }
}

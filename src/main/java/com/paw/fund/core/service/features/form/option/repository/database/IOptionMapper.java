package com.paw.fund.core.service.features.form.option.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.form.option.Option;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IOptionMapper extends IEntityMapper<OptionEntity, Option> {
    void update(@MappingTarget OptionEntity entity, Option dto);
}

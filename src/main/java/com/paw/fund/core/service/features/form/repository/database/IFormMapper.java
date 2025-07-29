package com.paw.fund.core.service.features.form.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.form.Form;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IFormMapper extends IEntityMapper<FormEntity, Form> {
    void update(@MappingTarget FormEntity entity, Form dto);
}

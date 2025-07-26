package com.paw.fund.core.service.features.account.license.content.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAccountLicenseContentMapper extends IEntityMapper<AccountLicenseContentEntity, AccountLicenseContent> {
    void update(@MappingTarget AccountLicenseContentEntity entity, AccountLicenseContent dto);
}

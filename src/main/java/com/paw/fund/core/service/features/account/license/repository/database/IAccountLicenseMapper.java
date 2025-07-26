package com.paw.fund.core.service.features.account.license.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.account.license.AccountLicense;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAccountLicenseMapper extends IEntityMapper<AccountLicenseEntity, AccountLicense> {
    void update(@MappingTarget AccountLicenseEntity entity, AccountLicense dto);
}

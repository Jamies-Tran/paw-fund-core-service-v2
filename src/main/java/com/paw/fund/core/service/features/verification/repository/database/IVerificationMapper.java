package com.paw.fund.core.service.features.verification.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.verification.Verification;
import com.paw.fund.core.service.domain.verification.VerificationAccount;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IVerificationMapper extends IEntityMapper<VerificationEntity, Verification> {
    VerificationAccount toDto(VerificationAccountDao dao);
}

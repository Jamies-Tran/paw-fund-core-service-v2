package com.paw.fund.core.service.features.account.license.controller.models;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.account.license.AccountLicense;
import com.paw.fund.core.service.features.account.license.controller.models.content.IAccountLicenseContentRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IAccountLicenseContentRequestMapper.class}
)
public interface IAccountLicenseRequestMapper extends IModelMapper<AccountLicenseRequest, AccountLicense> {
}

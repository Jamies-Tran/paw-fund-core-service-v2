package com.paw.fund.core.service.features.account.license.controller.models.content;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IAccountLicenseContentResponseMapper extends IModelMapper<AccountLicenseContentResponse, AccountLicenseContent> {
}

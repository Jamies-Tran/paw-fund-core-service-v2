package com.paw.fund.core.service.features.account.license.controller.models.content;


import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface IAccountLicenseContentRequestMapper extends IModelMapper<AccountLicenseContentRequest, AccountLicenseContent> {
}

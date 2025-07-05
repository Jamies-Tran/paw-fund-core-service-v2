package com.paw.fund.core.service.features.login.info.controller.models.mapper;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.features.account.controller.models.mapper.IRoleResponseModelMapper;
import com.paw.fund.core.service.features.login.info.controller.models.login.account.LoginAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = IRoleResponseModelMapper.class
)
public interface ILoginAccountResponseMapper extends IModelMapper<LoginAccountResponse, LoginAccount> {
}

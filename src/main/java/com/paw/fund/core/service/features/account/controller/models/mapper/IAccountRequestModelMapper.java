package com.paw.fund.core.service.features.account.controller.models.mapper;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = IMediaRequestModelMapper.class
)
public interface IAccountRequestModelMapper extends IModelMapper<AccountRequest, Account> {
}

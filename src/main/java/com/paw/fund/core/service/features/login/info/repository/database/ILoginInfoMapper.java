package com.paw.fund.core.service.features.login.info.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.domain.login.info.LoginInfo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ILoginInfoMapper extends IEntityMapper<LoginInfoEntity, LoginInfo> {
    LoginAccount toDto(LoginAccountDao dao);
}

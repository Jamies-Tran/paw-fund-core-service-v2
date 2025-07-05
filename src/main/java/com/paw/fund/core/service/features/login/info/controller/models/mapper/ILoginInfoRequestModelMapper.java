package com.paw.fund.core.service.features.login.info.controller.models.mapper;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.login.info.LoginInfo;
import com.paw.fund.core.service.features.login.info.controller.models.LoginInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ILoginInfoRequestModelMapper extends IModelMapper<LoginInfoRequest, LoginInfo> {
}

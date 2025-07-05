package com.paw.fund.core.service.features.account.controller.models.mapper;

import com.paw.fund.core.service.bootstrap.config.mapper.IModelMapper;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.features.account.controller.models.role.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IRoleResponseModelMapper extends IModelMapper<RoleResponse, Role> {
}

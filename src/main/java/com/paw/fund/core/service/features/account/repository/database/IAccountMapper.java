package com.paw.fund.core.service.features.account.repository.database;

import com.paw.fund.core.service.bootstrap.config.mapper.IEntityMapper;
import com.paw.fund.core.service.bootstrap.utils.PPasswordEncoder;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAccountMapper extends IEntityMapper<AccountEntity, Account> {
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "statusCode", defaultExpression = "java(defaultStatusCode())")
    @Mapping(target = "statusName", defaultExpression = "java(defaultStatusName())")
    AccountEntity toEntity(Account dto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    void update(@MappingTarget AccountEntity entity, Account dto);

    @Mapping(target = "password", ignore = true)
    void updateWithEmail(@MappingTarget AccountEntity entity, Account dto);

    @Named("encodePassword")
    default String encodePassword(String password) {
        return PPasswordEncoder.passwordEncoder().encode(password);
    }

    @Named("defaultStatusCode")
    default String defaultStatusCode() {
        return EAccountStatus.INACTIVE.getCode();
    }

    @Named("defaultStatusName")
    default String defaultStatusName() {
        return EAccountStatus.INACTIVE.getName();
    }
}

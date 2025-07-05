package com.paw.fund.core.service.features.login.info.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.login.info.LoginInfo;
import com.paw.fund.core.service.domain.login.info.enums.ELoginStatus;
import com.paw.fund.core.service.features.login.info.repository.database.ILoginInfoMapper;
import com.paw.fund.core.service.features.login.info.repository.database.ILoginInfoRepository;
import com.paw.fund.core.service.features.login.info.repository.database.LoginInfoEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoCommandService {
    ILoginInfoRepository repository;

    ILoginInfoMapper mapper;

    LoginInfo save(@NonNull LoginInfo loginInfo) {
        LoginInfoEntity newLoginInfo = mapper.toEntity(loginInfo);
        repository.save(newLoginInfo);

        return mapper.toDto(newLoginInfo);
    }

    void updateStatus(@NonNull Long accountId,@NonNull ELoginStatus status) {
        repository.findTopByAccountIdOrderByCreatedAtDesc(accountId)
                .ifPresentOrElse(
                        loginInfo -> {
                            loginInfo.setStatusCode(status.getCode());
                            loginInfo.setStatusName(status.getName());
                            repository.save(loginInfo);
                        },
                        () -> {
                            throw new PResourceNotFoundException();
                        }
                );
    }
}

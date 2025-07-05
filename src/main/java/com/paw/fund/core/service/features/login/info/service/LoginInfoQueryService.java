package com.paw.fund.core.service.features.login.info.service;

import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.features.login.info.repository.database.ILoginInfoMapper;
import com.paw.fund.core.service.features.login.info.repository.database.ILoginInfoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoQueryService {
    ILoginInfoRepository repository;

    ILoginInfoMapper mapper;

    Optional<LoginAccount> findLoginAccountByEmail(String email) {
        return repository.findLoginAccountByEmail(email)
                .map(mapper::toDto);
    }
}

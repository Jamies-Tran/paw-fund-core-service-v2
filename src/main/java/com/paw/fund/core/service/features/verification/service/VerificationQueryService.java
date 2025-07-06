package com.paw.fund.core.service.features.verification.service;

import com.paw.fund.core.service.domain.verification.VerificationAccount;
import com.paw.fund.core.service.features.verification.repository.database.IVerificationMapper;
import com.paw.fund.core.service.features.verification.repository.database.IVerificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationQueryService {
    IVerificationRepository repository;

    IVerificationMapper mapper;

    protected Optional<VerificationAccount> findVerificationAccountByEmail(String email) {
        return repository.findVerificationAccountByEmail(email)
                .map(mapper::toDto);
    }
}

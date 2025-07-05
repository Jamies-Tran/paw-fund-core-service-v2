package com.paw.fund.core.service.features.verification.service;

import com.paw.fund.core.service.domain.verification.Verification;
import com.paw.fund.core.service.features.verification.repository.database.IVerificationMapper;
import com.paw.fund.core.service.features.verification.repository.database.IVerificationRepository;
import com.paw.fund.core.service.features.verification.repository.database.VerificationEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class VerificationCommandService {
    IVerificationRepository repository;

    IVerificationMapper mapper;

    protected void save(Verification verification) {
        VerificationEntity save = mapper.toEntity(verification);

        repository.save(save);
    }
}

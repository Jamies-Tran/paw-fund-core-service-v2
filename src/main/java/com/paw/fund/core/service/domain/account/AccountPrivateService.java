package com.paw.fund.core.service.domain.account;

import com.paw.fund.core.service.domain.account.role.IAccountRoleUseCase;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.domain.media.IMediaUseCase;
import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import com.paw.fund.core.service.domain.role.enums.ERole;
import com.paw.fund.core.service.domain.verification.IVerificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AccountPrivateService {
    @Autowired
    IMediaUseCase mediaUseCase;

    @Autowired
    IRoleUseCase roleUseCase;

    @Autowired
    IAccountRoleUseCase accountRoleUseCase;

    @Autowired
    ILoginInfoUseCase loginInfoUseCase;

    @Autowired
    IVerificationUseCase verificationUseCase;

    protected void saveMedia(Long accountId, List<Media> medias) {
        mediaUseCase.saveAll(accountId, medias);
    }

    protected void saveAccountRole(Long accountId, Long shelterId, ERole role) {
        accountRoleUseCase.save(accountId, role, shelterId);
    }

    protected void saveAccountRole(Long accountId, ERole role) {
        accountRoleUseCase.save(accountId, role, null);
    }

    protected List<Role> roles(Long accountId) {
        return roleUseCase.findAllByAccountId(accountId);
    }

    protected List<Media> medias(Long accountId) {
        return mediaUseCase.findAllByAccountId(accountId);
    }

    protected void deleteVerification(String verificationCode) {
        verificationUseCase.delete(verificationCode);
    }

    protected LoginAccount loginAccount() {
        return loginInfoUseCase.getCurrentAccountLogin()
                .orElse(LoginAccount.empty());
    }
}

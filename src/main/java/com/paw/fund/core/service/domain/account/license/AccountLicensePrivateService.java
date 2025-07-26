package com.paw.fund.core.service.domain.account.license;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.domain.account.license.content.IAccountLicenseContentUseCase;
import com.paw.fund.core.service.domain.license.template.ILicenseTemplateUseCase;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AccountLicensePrivateService {
    @Autowired
    IAccountLicenseContentUseCase contentUseCase;

    @Autowired
    ILicenseTemplateUseCase licenseTemplateUseCase;

    @Autowired
    ILoginInfoUseCase loginInfoUseCase;

    protected void saveContents(Long accountLicenseId, List<AccountLicenseContent> contents) {
        contentUseCase.save(accountLicenseId, contents);
    }

    protected List<AccountLicenseContent> contents(Long accountLicenseId) {
        return contentUseCase.findAllByAccountLicenseId(accountLicenseId);
    }

    protected void updateContents(Long accountLicenseId, List<AccountLicenseContent> contents) {
        contentUseCase.update(accountLicenseId, contents);
    }

    protected LoginAccount loginAccount() {
        return loginInfoUseCase.getCurrentAccountLogin()
                .orElse(LoginAccount.empty());
    }

    protected LicenseTemplate licenseTemplate(Long licenseTemplateId) {
        return licenseTemplateUseCase.findById(licenseTemplateId)
                .orElseThrow(PResourceNotFoundException::new);
    }
}

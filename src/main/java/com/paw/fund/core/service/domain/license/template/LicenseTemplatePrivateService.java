package com.paw.fund.core.service.domain.license.template;

import com.paw.fund.core.service.domain.license.section.ILicenseTemplateSectionUseCase;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class LicenseTemplatePrivateService {
    @Autowired
    ILoginInfoUseCase loginInfoUseCase;

    @Autowired
    ILicenseTemplateSectionUseCase sectionUseCase;

    protected LoginAccount loginAccount() {
        return loginInfoUseCase.getCurrentAccountLogin()
                .orElse(LoginAccount.empty());
    }

    protected List<LicenseTemplateSection> licenseTemplateSections(Long licenseTemplateId) {
        return sectionUseCase.findAllByLicenseTemplateId(licenseTemplateId);
    }
}

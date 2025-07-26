package com.paw.fund.core.service.domain.account.license.content;

import java.util.List;

public interface IAccountLicenseContentUseCase {
    void save(Long accountLicenseId, List<AccountLicenseContent> contents);

    List<AccountLicenseContent> findAllByAccountLicenseId(Long accountLicenseId);

    void update(Long accountLicenseId, List<AccountLicenseContent> contents);
}

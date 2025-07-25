package com.paw.fund.core.service.domain.account;

import com.paw.fund.core.service.features.account.repository.database.AccountEntity;

public abstract class AccountCommandPrivateService {
    protected abstract void validateSave(Account account);

    protected abstract void validateUpdate(AccountEntity foundAccount, Account account);
}

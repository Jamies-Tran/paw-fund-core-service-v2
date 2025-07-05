package com.paw.fund.core.service.features.login.info.repository.database;

public interface LoginAccountDao {
    Long getAccountId();

    String getEmail();

    String getStatusCode();
}

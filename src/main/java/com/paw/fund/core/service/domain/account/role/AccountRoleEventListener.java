package com.paw.fund.core.service.domain.account.role;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleEventListener extends ApplicationEvent {
    Long accountId;
    @NonFinal Long shelterId;
    String roleCode;

    public AccountRoleEventListener(Object source, @NonNull Long accountId, @NonNull String roleCode) {
        super(source);
        this.accountId = accountId;
        this.roleCode = roleCode;
    }

    public AccountRoleEventListener(Object source, @NonNull Long accountId, Long shelterId, @NonNull String roleCode) {
        super(source);
        this.accountId = accountId;
        this.shelterId = shelterId;
        this.roleCode = roleCode;
    }
}

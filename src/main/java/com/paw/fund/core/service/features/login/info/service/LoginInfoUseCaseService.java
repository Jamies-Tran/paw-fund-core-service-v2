package com.paw.fund.core.service.features.login.info.service;

import com.paw.fund.core.service.bootstrap.config.handler.exception.PAuthenticationException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.bootstrap.utils.PPasswordEncoder;
import com.paw.fund.core.service.bootstrap.utils.PTokenUtils;
import com.paw.fund.core.service.domain.account.enums.EAccountStatus;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.domain.login.info.LoginInfo;
import com.paw.fund.core.service.domain.login.info.enums.ELoginStatus;
import com.paw.fund.core.service.domain.role.IRoleUseCase;
import com.paw.fund.core.service.domain.role.Role;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoUseCaseService implements ILoginInfoUseCase {
    LoginInfoCommandService commandService;

    LoginInfoQueryService queryService;

    PTokenUtils tokenUtil;

    IRoleUseCase roleUseCase;

    @Override
    @Transactional
    public LoginInfo auth(
            @NonNull String email,
            @NonNull String password,
            BigDecimal latitude,
            BigDecimal longitude
    ) {
        LoginAccount loginAccount = queryService.findLoginAccountByEmail(email)
                .orElseThrow(PResourceNotFoundException::new);
        if (!PObjectUtils.isEqual(EAccountStatus.ACTIVE.getCode(), loginAccount.statusCode())) {
            throw new PResourceNotFoundException("Tài khoản chưa được kích hoạt");
        }
        if (!PPasswordEncoder.passwordEncoder().matches(password, loginAccount.password())) {
            throw new PAuthenticationException("Xác nhận tài khoản thất bại");
        }
        List<Role> roles = roleUseCase.findAllByAccountId(loginAccount.accountId());
        String accessToken = tokenUtil.generateAccessToken(loginAccount.email());
        String refreshToken = UUID.randomUUID().toString();
        LocalDateTime accessExpiredAt = tokenUtil.accessTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime refreshExpiredAt = tokenUtil.refreshTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LoginInfo loginInfo = LoginInfo.builder()
                .accountId(loginAccount.accountId())
                .accountEmail(loginAccount.email())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredAt(accessExpiredAt)
                .refreshExpiredAt(refreshExpiredAt)
                .latitude(latitude)
                .longitude(longitude)
                .statusCode(ELoginStatus.LOGIN.getCode())
                .statusName(ELoginStatus.LOGIN.getName())
                .build();

        return commandService.save(loginInfo)
                .withAccessToken(accessToken)
                .withAccount(loginAccount.withRoles(roles));
    }

    @Override
    @Transactional(readOnly = true)
    public LoginAccount getCurrentAccountLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return queryService
                .findLoginAccountByEmail((String) securityContext.getAuthentication().getPrincipal())
                .orElse(null);
    }

    @Override
    @Transactional
    public void logout() {
        LoginAccount loginAccount = getCurrentAccountLogin();
        commandService.updateStatus(loginAccount.accountId(), ELoginStatus.LOGOUT);
    }
}

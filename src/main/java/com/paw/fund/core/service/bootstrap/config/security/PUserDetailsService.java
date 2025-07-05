package com.paw.fund.core.service.bootstrap.config.security;

import com.paw.fund.core.service.bootstrap.utils.PPasswordEncoder;
import com.paw.fund.core.service.domain.account.Account;
import com.paw.fund.core.service.domain.account.IAccountUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PUserDetailsService implements UserDetailsService {
    IAccountUseCase accountUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountUseCase.findByEmail(username);

        return account
                .map(account1 -> {
                    List<SimpleGrantedAuthority> authorities = account1.roles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_%s".formatted(role.roleCode())))
                            .toList();
                    return User.builder()
                            .username(account1.email())
                            .password(PPasswordEncoder.passwordEncoder().encode(account1.password()))
                            .disabled(false)
                            .authorities(authorities)
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

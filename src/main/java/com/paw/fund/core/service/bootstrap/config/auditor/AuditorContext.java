package com.paw.fund.core.service.bootstrap.config.auditor;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorContext {
    public static String getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(PObjectUtils.isNull(auth) || !auth.isAuthenticated()) {
            return "Anonymous";
        }

        return (String) auth.getPrincipal();
    }
}

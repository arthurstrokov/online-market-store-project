package com.gmail.arthurstrokov.service.util;

import com.gmail.arthurstrokov.service.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CurrentUser {

    private CurrentUser() {
    }

    private static UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    public static Collection<? extends GrantedAuthority> getCurrentAuthorities() {
        return getCurrentUser().getAuthorities();
    }

    public static Long getCurrentId() {
        return getCurrentUser().getId();
    }
}

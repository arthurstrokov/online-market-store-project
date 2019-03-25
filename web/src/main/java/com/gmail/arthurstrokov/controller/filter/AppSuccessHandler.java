package com.gmail.arthurstrokov.controller.filter;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.dao.model.RoleConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collection;

@Component("appSuccessHandler")
public class AppSuccessHandler implements AuthenticationSuccessHandler, RoleConstant {

    private static final Logger logger = LogManager.getLogger(AppSuccessHandler.class.getName());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final PageProperties pageProperties;

    @Autowired
    public AppSuccessHandler(
            @Qualifier("pageProperties") PageProperties pageProperties
    ) {
        this.pageProperties = pageProperties;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        String targetUrl = determinatedTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.info("Response has already been commited. Unable to redirect to {}", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determinatedTargetUrl(Authentication authentication) throws AccessDeniedException {
        boolean isSecurityUser = false;
        boolean isSaleUser = false;
        boolean isCustomerUser = false;
        boolean isApiUser = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case SECURITY:
                    isSecurityUser = true;
                    break;
                case SALE:
                    isSaleUser = true;
                    break;
                case CUSTOMER:
                    isCustomerUser = true;
                    break;
                case API:
                    isApiUser = true;
                    break;
            }
        }
        if (isSecurityUser) {
            return pageProperties.getUsersPagePath();
        } else if (isSaleUser) {
            return pageProperties.getStorePagePath();
        } else if (isCustomerUser) {
            return pageProperties.getItemsPagePath();
        } else if (isApiUser) {
            throw new AccessDeniedException("Access denied!");
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}

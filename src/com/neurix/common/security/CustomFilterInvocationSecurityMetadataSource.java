package com.neurix.common.security;

import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.role.model.Roles;
import com.neurix.authorization.user.model.UserDetailsLogin;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 06/02/13
 * Time: 8:44
 * To change this template use File | Settings | File Templates.
 */
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    protected static transient Logger logger = Logger.getLogger(CustomFilterInvocationSecurityMetadataSource.class);

    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        logger.info("[CustomFilterInvocationSecurityMetadataSource.getAttributes] start process >>>");

        FilterInvocation fI = (FilterInvocation) o;
        Object principal = null;
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            logger.error("[CustomFilterInvocationSecurityMetadataSource.getAttributes] An Authentication object was not found in the SecurityContext. No User Login for this URL.");
            throw new AuthenticationCredentialsNotFoundException("An Authentication object was not found in the SecurityContext");
        } else {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        //to strip of query string from url.
        String url = fI.getRequestUrl();
        if (url.indexOf('?') != -1)
            url = url.substring(0, url.indexOf('?'));
        url = url.trim();

        logger.info("[CustomFilterInvocationSecurityMetadataSource.getAttributes] URL will be filter =  " + url);

        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
        UserDetailsLogin userDetailsLogin = (UserDetailsLogin) principal;

        //This code for role based url access.
        //Here the user bean contains a set of functions with urls. We are comparing the current url with the set of urls
        // allowed for this user .
        if (userDetailsLogin.getRoles() != null) {
            List<Roles> listOfRoles = userDetailsLogin.getRoles();

            for (Roles roles : listOfRoles) {
                List<Functions> listOfFunctionBasedRole = roles.getListFunctions();
                for (Functions funcBasedRole : listOfFunctionBasedRole) {
                    if (StringUtils.isNotEmpty(funcBasedRole.getUrl()) && (funcBasedRole.getUrl().toLowerCase()).contains(url.toLowerCase())) {
                        logger.info("[CustomFilterInvocationSecurityMetadataSource.getAttributes] Match filter from userLogin =  " + funcBasedRole.getFuncName() + "-> " + funcBasedRole.getUrl());
                        attributes.add(new SecurityConfig("IS_AUTHENTICATED_FULLY"));
                        break;
                    }
                }

            }

        }

        if (attributes.isEmpty()) {
            logger.info("[CustomFilterInvocationSecurityMetadataSource.getAttributes] NOT Match url with userLogin roles func. Access Denied !!");
            attributes.add(new SecurityConfig("IS_NOT_AUTHENTICATED_FULLY"));
        }

        logger.info("[CustomFilterInvocationSecurityMetadataSource.getAttributes] end process <<<");

        return attributes;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}

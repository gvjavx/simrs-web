package com.neurix.common.security;

import com.neurix.authorization.user.model.UserDetailsLogin;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 09/01/13
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public class CustomRememberMeServices extends AbstractRememberMeServices {

    protected static transient Logger logger = Logger.getLogger(CustomRememberMeServices.class);

    @Override
    protected void onLoginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

        logger.info("[CustomRememberMeServices.onLoginSuccess] on login success remember me.");

    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] strings, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws RememberMeAuthenticationException, UsernameNotFoundException {

        logger.info("[CustomRememberMeServices.processAutoLoginCookie] Start process Autologin rememberme.");

        HttpSession session = httpServletRequest.getSession();
        UserDetailsLogin userDetailsLogin = null;

        if (session==null) throw new UsernameNotFoundException("Can not load user details via cookie/session." );
        else {

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SessionRegistryImpl sessionRegistry = (SessionRegistryImpl) ctx.getBean("sessionRegistry");

            SessionInformation sessionInfo = (sessionRegistry != null ? sessionRegistry.getSessionInformation(session.getId()) : null);

            if (sessionInfo != null) {
                userDetailsLogin = (UserDetailsLogin) sessionInfo.getPrincipal();
            }
        }

        logger.info("[CustomRememberMeServices.processAutoLoginCookie] End process Autologin rememberme.");

        return userDetailsLogin;
    }
}

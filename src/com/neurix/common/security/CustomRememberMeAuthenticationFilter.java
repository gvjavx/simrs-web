package com.neurix.common.security;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.UserDetailsLogin;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 09/01/13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class CustomRememberMeAuthenticationFilter extends RememberMeAuthenticationFilter {

    protected static transient Logger logger = Logger.getLogger(CustomRememberMeAuthenticationFilter.class);
    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        super.onSuccessfulAuthentication(request, response, authResult);
        logger.info("[CustomRememberMeAuthenticationFilter.onSuccessfulAuthentication] start process >>>");

        logger.info("[CustomRememberMeAuthenticationFilter.onSuccessfulAuthentication] Remember me login success for user : " + authResult.getPrincipal().toString());

        String sessionId = request.getSession()!=null ? request.getSession().getId() : null;

        if (sessionId!=null && "".equalsIgnoreCase(sessionId)) {
            UserDetailsLogin userDetailsLogin = (UserDetailsLogin) authResult.getPrincipal();
            if (userDetailsLogin!=null) {

                WebAuthenticationDetails webAuthenticationDetails=(WebAuthenticationDetails)authResult.getDetails();
                String ipLogin=webAuthenticationDetails.getRemoteAddress();

                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                try {
                    userBo.insertUserSessionLog(sessionId,userDetailsLogin,ipLogin);
                } catch (HibernateException e) {
                    logger.error("[CustomRememberMeAuthenticationFilter.onSuccessfulAuthentication] Error while updating session log." ,e );
                }

            }
        }

        logger.info("[CustomRememberMeAuthenticationFilter.onSuccessfulAuthentication] end process <<<");    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        super.onUnsuccessfulAuthentication(request, response, failed);    //To change body of overridden methods use File | Settings | File Templates.

        logger.info("[CustomRememberMeAuthenticationFilter.onSuccessfulAuthentication] Remember me login failed.");

    }
}

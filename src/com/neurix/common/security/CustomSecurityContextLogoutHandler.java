package com.neurix.common.security;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.UserDetailsLogin;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 10/01/13
 * Time: 0:23
 * To change this template use File | Settings | File Templates.
 */
public class CustomSecurityContextLogoutHandler extends SecurityContextLogoutHandler {

    protected static transient Logger logger = Logger.getLogger(CustomSecurityContextLogoutHandler.class);

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        logger.info("[CustomSecurityContextLogoutHandler.logout] start process >>>");

        Cookie[] allCookies = request.getCookies();
        if (allCookies != null) {
            for (int i = 0; i < allCookies.length; i++) {
                String name = allCookies[i].getName();
                if (name.equalsIgnoreCase("JSESSIONID")) {
                    logger.info("[CustomSecurityContextLogoutHandler.logout] clear cookies [" + " Name=" + name + ", Value=" + allCookies[i].getValue() + " ]");
                    Cookie cookieToDelete = allCookies[i];
                    cookieToDelete.setValue("");
                    cookieToDelete.setMaxAge(0);
                    cookieToDelete.setVersion(0);
                    cookieToDelete.setPath("/");
                    response.addCookie(cookieToDelete);
                }
            }
        }
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                if (userDetails != null) {
                    String sessionId = request.getSession().getId();

                    if (sessionId != null) {

                        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                        try {
                            userBo.updateUserSessionLog(sessionId);
                        } catch (HibernateException e) {
                            logger.error("[CustomSecurityContextLogoutHandler.logout] Error while updating session log.", e);
                        }

                        authentication.setAuthenticated(false);
                        userDetails = new UserDetailsLogin();

                        logger.info("[CustomSecurityContextLogoutHandler.logout] session active will logout : " + userDetails.toString());
                    }
                }
            }
        }

        request.getSession().invalidate();

        super.logout(request, response, authentication);

        logger.info("[CustomSecurityContextLogoutHandler.logout] end process <<<");
    }
}

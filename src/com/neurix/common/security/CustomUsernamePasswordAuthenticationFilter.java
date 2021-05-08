package com.neurix.common.security;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.constant.CommonConstant;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 09/01/13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    protected static transient Logger logger = Logger.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authResult)
            throws IOException, ServletException {

        super.successfulAuthentication(request, response, authResult);

        logger.info("[CustomUsernamePasswordAuthenticationFilter.successfulAuthentication] start process >>>");

        logger.info("[CustomUsernamePasswordAuthenticationFilter.successfulAuthentication] login sucess for user : " + authResult.getPrincipal().toString());

        String sessionId = request.getSession()!=null ? request.getSession().getId() : null;

        if (sessionId!=null && !"".equalsIgnoreCase(sessionId)) {
            UserDetailsLogin userDetailsLogin = (UserDetailsLogin) authResult.getPrincipal();
            if (userDetailsLogin!=null) {
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

                String pathImage = "";
                String pathName = CommonConstant.RESOURCE_PATH_USER_PHOTO;

                UserDetailsLogin readUserDetailsLogin = null;
                String username=userDetailsLogin.getUsername();

                try {
                    readUserDetailsLogin = userBo.retrievePhotoUser(pathName, username);
                } catch (HibernateException e) {
                    logger.error("[CustomUsernamePasswordAuthenticationFilter.successfulAuthentication] Error while retrive user photo,", e);
                } catch (IOException e) {
                    logger.error("[CustomUsernamePasswordAuthenticationFilter.successfulAuthentication] Error while retrive user photo, ", e);

                }

                if (readUserDetailsLogin != null) {
                    pathImage = readUserDetailsLogin.getPhotoUserUrl();
                    userDetailsLogin.setPhotoUserUrl(pathImage);
                }

                WebAuthenticationDetails webAuthenticationDetails=(WebAuthenticationDetails)authResult.getDetails();
                String ipLogin=webAuthenticationDetails.getRemoteAddress();

                try {
                    userBo.insertUserSessionLog(sessionId,userDetailsLogin,ipLogin);
                } catch (HibernateException e) {
                    logger.error("[CustomUsernamePasswordAuthenticationFilter.successfulAuthentication] Error while updating session log." ,e );
                }

            }
        }

        logger.info("[CustomUsernamePasswordAuthenticationFilter.successfulAuthentication] end process <<<");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {

        super.unsuccessfulAuthentication(request, response, failed);

        logger.info("[CustomUsernamePasswordAuthenticationFilter.unsuccessfulAuthentication] login failed,  " + failed.getMessage());

    }

}
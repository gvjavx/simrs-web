package com.neurix.common.security;

import com.neurix.authorization.user.bo.UserBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 14/01/13
 * Time: 2:08
 * To change this template use File | Settings | File Templates.
 */
public class CustomHttpSessionEventPublisher extends HttpSessionEventPublisher {

    protected static transient Logger logger = Logger.getLogger(CustomHttpSessionEventPublisher.class);
    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info("[CustomHttpSessionEventPublisher.sessionCreated] Start created session....");

        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);

        super.sessionCreated(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

        logger.info("[CustomHttpSessionEventPublisher.sessionDestroyed] Start destroy session.");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SessionRegistryImpl sessionRegistry = (SessionRegistryImpl) ctx.getBean("sessionRegistry");
        SessionInformation sessionInfo = (sessionRegistry != null ? sessionRegistry.getSessionInformation(event.getSession().getId()) : null);

        UserDetails userDetails = null;

        if (sessionInfo != null) {
            userDetails = (UserDetails) sessionInfo.getPrincipal();

            if (userDetails != null) {
                String sessionId = event.getSession().getId();

                if (sessionId != null) {
                    UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                    try {
                        userBo.updateUserSessionLog(sessionId);
                    } catch (HibernateException e) {
                        logger.error("[CustomHttpSessionEventPublisher.sessionDestroyed] Error while updating session log." ,e );
                    }

                    logger.info("[CustomHttpSessionEventPublisher.sessionDestroyed] session active will destroyed : " + userDetails.getUsername() + "," + userDetails.getAuthorities() );
                }
            }

            sessionRegistry.removeSessionInformation(event.getSession().getId());
        }

        sessions.remove(event.getSession().getId());

        super.sessionDestroyed(event);

        logger.info("[CustomHttpSessionEventPublisher.sessionDestroyed] End destroy session.");
    }

    public static HttpSession find(String sessionId) {
        return sessions.get(sessionId);
    }
}
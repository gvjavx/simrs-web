package com.neurix.common.security;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.UserDetailsLogin;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 04/11/12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class CustomUserDetailService implements UserDetailsService {

    protected static transient Logger logger = Logger.getLogger(CustomUserDetailService.class);

    private UserBo userBo;
    private SessionRegistry sessionRegistry;

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setUserBo(UserBo userBo) {
        this.userBo = userBo;
    }

    public UserDetailsLogin loadUserByUsername(String username)
            throws UsernameNotFoundException {

        logger.info("[CustomUserDetailService.loadUserByUsername] start process >>>");

//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        HttpSession session = ServletActionContext.getRequest().getSession();

        SessionRegistryImpl sessionRegistry = (SessionRegistryImpl) getSessionRegistry();

//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String contexPath=attr.getRequest().getContextPath();
//        HttpSession session= attr.getRequest().getSession(true);

//        SessionRegistryImpl sessionRegistry = (SessionRegistryImpl) ctx.getBean("sessionRegistry");

        String sessionId=RequestContextHolder.currentRequestAttributes().getSessionId();

        SessionInformation sessionInfo = (sessionRegistry != null ? sessionRegistry.getSessionInformation(sessionId) : null);

//        SessionInformation sessionInfo = (sessionRegistry != null ? sessionRegistry.getSessionInformation(session.getId()) : null);

        UserDetails userDetails = null;
        UserDetailsLogin userDetailsLogin = null;
        if (sessionInfo != null) {
            userDetails = (UserDetails) sessionInfo.getPrincipal();

            if (userDetails != null) {
                if (username.equalsIgnoreCase(userDetails.getUsername())) {
                    userDetailsLogin = (UserDetailsLogin) userDetails;
                    logger.info("[CustomUserDetailService.loadUserByUsername] User has been login, only one user login to system, " + username);
                } else {
//                    UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

                    try {
                        userDetailsLogin = userBo.getUserByUsername(username, "Y", contexPath);

                        if (userDetailsLogin == null) {
                            logger.info("[CustomUserDetailService.loadUserByUsername] User not found, username = " + username);
                            throw new UsernameNotFoundException("User Not Found, Please check again your username.");
                        }

                    } catch (HibernateException e) {
                        logger.error("[CustomSecurityContextLogoutHandler.logout] Error while retrieving user.", e);
                    } catch (UsernameNotFoundException e) {
                        logger.error("[CustomSecurityContextLogoutHandler.logout] Error while retrieving user.", e);
                    }

                }

            } else {

//                UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                userDetailsLogin = userBo.getUserByUsername(username, "Y", contexPath);

                if (userDetailsLogin == null) {
                    logger.info("[CustomUserDetailService.loadUserByUsername] User not found, username = " + username);
                    throw new UsernameNotFoundException("User Not Found, Please check again your username.");
                }
            }
        } else {
//            UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
            userDetailsLogin = userBo.getUserByUsername(username, "Y", contexPath);

            if (userDetailsLogin == null) {
                logger.info("[CustomUserDetailService.loadUserByUsername] User not found, username = " + username);
                throw new UsernameNotFoundException("User Not Found, Please check again your username.");
            }
        }

        logger.info("[CustomUserDetailService.loadUserByUsername] end process <<<");

        return userDetailsLogin;

    }

}

package com.neurix.authorization.user.action;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.constant.CommonConstant;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 14/01/13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class UserLoginAction extends ActionSupport {

    protected static transient Logger logger = Logger.getLogger(UserLoginAction.class);

    private String userName;
    private boolean flagError = false;
    private String messageError = "";
    private boolean flagSignUp = false;
    private String userPhotoUrl;
    private UserBo userBoProxy;
    private boolean redirectSession;

    public boolean isRedirectSession() {
        return redirectSession;
    }

    public void setRedirectSession(boolean redirectSession) {
        this.redirectSession = redirectSession;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public boolean isFlagError() {
        return flagError;
    }

    public void setFlagError(boolean flagError) {
        this.flagError = flagError;
    }

    public boolean isFlagSignUp() {
        return flagSignUp;
    }

    public void setFlagSignUp(boolean flagSignUp) {
        this.flagSignUp = flagSignUp;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String logout() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.invalidate();
        return SUCCESS;
    }

    public String login() {

        logger.info("[UserAction.login] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();

        if (!isRedirectSession()) {
            if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
                SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

                if (securityContextImpl.getAuthentication() != null) {
                    if (securityContextImpl.getAuthentication().isAuthenticated()) {

                        return "redirect";

                    } else {

                        String lastUserName = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");

                        //get user photo based on username
                        String message = retrievePhoto(lastUserName);

                        setMessageError(message);
                        setUserName(lastUserName);
                        setFlagError(true);
                    }

                } else {
                    if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {

                        Object obException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
                        if (obException instanceof AuthenticationServiceException) {

                            String lastException = ((AuthenticationServiceException) obException).getMessage();

                            if (lastException != null && !"".equalsIgnoreCase(lastException)) {

                                String lastUserName = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");

                                //get user photo based on username
                                String message = retrievePhoto(lastUserName);
                                setMessageError(message);

                                setUserName(lastUserName);
                                setFlagError(true);
                            }
                        } else if (obException instanceof BadCredentialsException) {
                            String lastUserName = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
                            //get user photo based on username
                            String message = retrievePhoto(lastUserName);
                            setMessageError(message);

                            setUserName(lastUserName);
                            setFlagError(true);

                        } else if (obException instanceof SessionAuthenticationException) {
                            String sessionException = ((SessionAuthenticationException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
                            if (sessionException != null && !"".equalsIgnoreCase(sessionException)) {
                                setMessageError(sessionException);
                                setFlagError(true);

                                setUserPhotoUrl(CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO);
                            }
                        } else {
                            String exception = ((Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
                            if (exception != null && !"".equalsIgnoreCase(exception)) {
                                setMessageError(exception);
                                setFlagError(true);

                                setUserPhotoUrl(CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO);
                            }
                        }
                    }
                }

            } else {
                if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {

                    Object obException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

                    if (obException instanceof AuthenticationServiceException) {
                        String lastException = ((AuthenticationServiceException) obException).getMessage();
                        if (lastException != null && !"".equalsIgnoreCase(lastException)) {

                            String lastUserName = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
                            //get user photo based on username
                            String message = retrievePhoto(lastUserName);
                            setMessageError(message);

                            setUserName(lastUserName);
                            setFlagError(true);
                        }
                    } else if (obException instanceof BadCredentialsException) {
                        String lastUserName = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
                        //get user photo based on username
                        String message = retrievePhoto(lastUserName);
                        setMessageError(message);

                        setUserName(lastUserName);
                        setFlagError(true);

                    } else if (obException instanceof SessionAuthenticationException) {
                        String sessionException = ((SessionAuthenticationException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
                        if (sessionException != null && !"".equalsIgnoreCase(sessionException)) {
                            setMessageError(sessionException);
                            setFlagError(true);

                            setUserPhotoUrl(CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO);
                        }
                    } else {
                        String exception = ((Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
                        if (exception != null && !"".equalsIgnoreCase(exception)) {
                            setMessageError(exception);
                            setFlagError(true);

                            setUserPhotoUrl(CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO);
                        }
                    }
                }
            }
        } else {
            addActionError("Session has been expired,please login again.");
        }

        logger.info("[UserAction.login] end process <<<");

        return INPUT;
    }

    public String showMainMenu() {

        return SUCCESS;
    }

    public String accessDenied() {

        return SUCCESS;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    private String retrievePhoto(String username) {

        logger.info("[UserAction.retrievePhotoUser] start process >>>");

        String message = null;
        if (username != null && !"".equalsIgnoreCase(username)) {

            String pathImage = "";
            String pathName = CommonConstant.RESOURCE_PATH_USER_PHOTO;

            UserDetailsLogin userDetailsLogin = null;
            boolean isValidUser = false;
            try {

                userDetailsLogin = userBoProxy.retrievePhotoUser(pathName, username);

                if (userDetailsLogin != null) {
                    isValidUser = userDetailsLogin.isEnabled();
                    pathImage = userDetailsLogin.getPhotoUserUrl();
                }
            } catch (HibernateException e) {
                logger.error("[UserAction.retrievePhotoUser] error while retrive user photo, ", e);
                isValidUser = false;
            } catch (IOException e) {
                logger.error("[UserAction.retrievePhotoUser] error while retrive user photo, ", e);
                isValidUser = false;
            }

            if (isValidUser) {
                this.flagSignUp = false;
                this.userPhotoUrl = pathImage;
                message = "Password is incorrect.";
            } else {
                flagSignUp = true;
                message = "Username or password is incorrect.";
            }

        } else {
            flagSignUp = true;
            message = "Username or password is incorrect.";
        }

        logger.info("[UserAction.retrievePhotoUser] end process <<<");

        return message;
    }


    // contoh menu yg akan dihasilkan method ini :
//    var menuItems = [
//		["Keuangan","", , , , , "0", "0", , ],
//	    ["|Akuntansi","", , , , , , , , ],
//	        ["||Master","", , , , , , , , ],
//	        	["|||Jenis Biaya","/simpat-pelindo4/anggaranakuntansi/jenisbiaya/jenisBiaya_input.action", , , , , , , , ],
//	        	["|||Mata Uang","/simpat-pelindo4/anggaranakuntansi/matauang/mataUang_input.action", , , , , , , , ],
//        ];
    public List getMenuUser() {
        logger.info("[UserAction.getMenuUser] start process >>>");

        List listdownMenu = new ArrayList();
        String error;
        HttpSession session = WebContextFactory.get().getSession();
        String contextPath = WebContextFactory.get().getHttpServletRequest().getContextPath();

        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

            if (securityContextImpl.getAuthentication() != null) {
                if (securityContextImpl.getAuthentication().isAuthenticated()) {

                    UserDetailsLogin userDetailsLogin = (UserDetailsLogin) securityContextImpl.getAuthentication().getPrincipal();

                    if (userDetailsLogin.getMenus() != null) {
                        if (userDetailsLogin.getMenus().isEmpty()) {
                            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                            UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                            try {

                                listdownMenu = userBo.getMenuUser(userDetailsLogin, contextPath);
                            } catch (HibernateException e) {
                                error = "ERROR";
                                listdownMenu.add(error);
                                logger.error("[UserAction.getMenuUser] Error while retrieving menu user.", e);
                            }

                            userDetailsLogin.setMenus(listdownMenu);

                        } else {
                            listdownMenu = userDetailsLogin.getMenus();
                        }
                    } else {

                        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                        try {
                            listdownMenu = userBo.getMenuUser(userDetailsLogin, contextPath);
                        } catch (HibernateException e) {
                            error = "ERROR";
                            listdownMenu.add(error);
                            logger.error("[UserAction.getMenuUser] Error while retrieving menu user.", e);
                        }

                        userDetailsLogin.setMenus(listdownMenu);

                    }

                } else {

                    error = "ERROR";
                    listdownMenu.add(error);
                }

            } else {

                error = "ERROR";
                listdownMenu.add(error);

            }

        } else {

            error = "ERROR";
            listdownMenu.add(error);

        }

        logger.info("[UserAction.getMenuUser] end process <<<");

        return listdownMenu;
    }

    public String dashboard() {

        return "success";
    }

}


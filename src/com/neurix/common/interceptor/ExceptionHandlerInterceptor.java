package com.neurix.common.interceptor;

/**
 * Created by Endro on 5/06/2018.
 */
import com.neurix.authorization.user.bo.UserBo;
import com.neurix.common.exception.GeneralBOException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.ContentTypeHandlerManager;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerInterceptor extends MethodFilterInterceptor {

    protected static transient Logger logger = Logger.getLogger(ExceptionHandlerInterceptor.class);
    private static final String ACTION_ERROR = "actionError";

    private ContentTypeHandlerManager manager;

    @Inject
    public void setContentTypeHandlerManager(ContentTypeHandlerManager mgr) {
        this.manager = mgr;
    }

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        try{

            logger.info("Checking token...");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            HttpServletRequest request = ServletActionContext.getRequest();
            String namespace = request.getServletPath();

            if ("/mobileapi/loginmobile".equalsIgnoreCase(namespace)) {
                String acceptHeader = request.getHeader("Accept");
                if (acceptHeader!=null) {
                    if (acceptHeader.equalsIgnoreCase("mobile")) {
                        //by pass checking token
                        logger.info("[ExceptionHandlerInterceptor.checkingSession] Loging in user mobile ...");

                    } else { //checking tokenId
                        String token = request.getHeader("tokenId");

                        logger.info("[ExceptionHandlerInterceptor.checkingSession] Login using token = " + token);

                        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                        boolean isActive = false;
                        try {
                            isActive = userBo.isActiveUserSessionLog(token);
                        } catch (HibernateException e) {
                            logger.error("[ExceptionHandlerInterceptor.checkingSession] Error while checking session log." ,e );
                            throw new GeneralBOException(e);
                        }

                        if (isActive) {
                            logger.info("[ExceptionHandlerInterceptor.checkingSession] this token still active.");
                        } else {
                            throw new GeneralBOException("[ReLogin] Your token is expired. Please re-Login again your apps.");
                        }

                    }
                } else {

                    throw new SecurityException("Your access is denied. Operation not allowed!");

                }
            } else if ("/mobileapi/plant".equalsIgnoreCase(namespace)) {
                logger.info("[ExceptionHandlerInterceptor.checkingApi] skiping token");
            }else if ("/mobileapi/cronproccess".equalsIgnoreCase(namespace)) {
                logger.info("[ExceptionHandlerInterceptor.cronproccess] skiping token");
            } else if ("/mobileapi/testbpjs".equalsIgnoreCase(namespace)) {
                logger.info("[ExceptionHandlerInterceptor.testbpjs] skiping token");
            }
            else {

                String token = request.getHeader("tokenId");
                if (token != null) {

                    logger.info("[ExceptionHandlerInterceptor.checkingSession] Login using token = " + token);

                    UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
                    boolean isActive = false;
                    try {
                        isActive = userBo.isActiveUserSessionLog(token);
                    } catch (HibernateException e) {
                        logger.error("[ExceptionHandlerInterceptor.checkingSession] Error while checking session log." ,e );
                        throw new GeneralBOException(e);
                    }

                    if (isActive) {
                        logger.info("[ExceptionHandlerInterceptor.checkingSession] this token still active.");
                    } else {
                        throw new GeneralBOException("[ReLogin] Your token is expired. Please re-Login again your apps.");
                    }

                } else {

                    throw new SecurityException("Your access is denied. Operation not allowed!");

                }

            }


            return actionInvocation.invoke();
        } catch (Exception exception){

            logger.info("[ExceptionHandlerInterceptor] Exception occurred: {}", exception);

            Map<String, Object> errors = new HashMap<>();

            HttpHeaders httpHeaders = new DefaultHttpHeaders()
                    .disableCaching()
                    .withStatus(HttpServletResponse.SC_BAD_REQUEST)
                    .renderResult(Action.INPUT);

            if (exception instanceof SecurityException) {
                errors.put(ACTION_ERROR, "Operation not allowed!");
                httpHeaders.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }  else {
                errors.put(ACTION_ERROR, exception.getMessage());
            }
            return manager.handleResult(actionInvocation.getProxy().getConfig(), httpHeaders, errors);
        }
    }
}
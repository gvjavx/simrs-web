package com.neurix.common.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 02/03/13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class SessionCheckInterceptor implements Interceptor {

    protected static transient Logger logger = Logger.getLogger(SessionCheckInterceptor.class);


    public void destroy() {
        logger.info("SessionCheckInterceptor destroy() is called...");
    }


    public void init() {
        logger.info("SessionCheckInterceptor init() is called...");
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext context = actionInvocation.getInvocationContext();
        Map<String, Object> sessionMap = context.getSession();

        if (sessionMap == null || sessionMap.isEmpty() || sessionMap.get("SPRING_SECURITY_CONTEXT") == null) {
            logger.info("Session expired ...");
//            addActionError(actionInvocation,"Session has been expired,please login again.");
            return "sessionexpired";
        }

        String actionResult = actionInvocation.invoke();
        return actionResult;
    }

    private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if(action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}
}

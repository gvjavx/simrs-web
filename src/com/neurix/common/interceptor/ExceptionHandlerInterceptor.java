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
            } else if (namespace.contains("/mobileapi/plant")) {
                logger.info("[ExceptionHandlerInterceptor.checkingApi] skiping token");
            }else if (namespace.contains("/mobileapi/cronproccess")) {
                logger.info("[ExceptionHandlerInterceptor.cronproccess] skiping token");
            } else if (namespace.contains("/mobileapi/bpjs")) {
                logger.info("[ExceptionHandlerInterceptor.testbpjs] skiping token");
            } else if (namespace.contains("/mobileapi/loginpasien")) {
                logger.info("[ExceptionHandlerInterceptor.loginpasien] skiping token");
            } else if (namespace.contains("/mobileapi/pelayanan")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            }else if (namespace.contains("/mobileapi/branch")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            }else if (namespace.contains("/mobileapi/registrasi")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/antrian")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/purchaseorder")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/permintaanobat")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/transaksiapotek")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/rawatinap")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/ruangan")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/cutipegawai")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            }  else if (namespace.contains("/mobileapi/ijinkeluar"))  {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/notifikasi"))  {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/biodataprofile")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/payroll"))  {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/viewpayroll")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            }  else if (namespace.contains("/mobileapi/sisacuti"))  {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            }  else if (namespace.contains("/mobileapi/lembur")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/branch")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/pengajuancuti")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/biodata")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/dispensasi")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/payrol")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/historycuti")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/checkup")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/user")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/absensi"))  {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/dokter")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            }else if (namespace.contains("/mobileapi/kasir")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/telemedicine")) {
                logger.info("[ExceptionHandlerInterceptor.pelayanan] skiping token");
            } else if (namespace.contains("/mobileapi/testelemedic")) {
                logger.info("[ExceptionHandlerInterceptor.testelemedic] skiping token");
            } else if (namespace.contains("/mobileapi/pembayaran")) {
                logger.info("[ExceptionHandlerInterceptor.testelemedic] skiping token");
            } else if (namespace.contains("/mobileapi/testnotif")) {
                logger.info("[ExceptionHandlerInterceptor.testnotif] skiping token");
            } else if (namespace.contains("/mobileapi/kurir")) {
                logger.info("[ExceptionHandlerInterceptor.testnotif] skiping token");
            } else if (namespace.contains("/mobileapi/reseponline")) {
                logger.info("[ExceptionHandlerInterceptor.reseponline] skiping token");
            } else if (namespace.contains("/mobileapi/pengiriman")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/asuransi")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            }  else if (namespace.contains("/mobileapi/rekening")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/historypegawai")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/jadwalshift")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/antrianpoli")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/company"))  {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/license")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/version")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            } else if (namespace.contains("/mobileapi/versionmobile")) {
                logger.info("[ExceptionHandlerInterceptor.pengiriman] skiping token");
            }else if (namespace.contains("/mobileapi/settlementinvoice")) { //updated by ferdi, 01-12-2020, for billing system payment gateway
                logger.info("[ExceptionHandlerInterceptor.billingsystem] skiping token");
            } else if (namespace.contains("/mobileapi/provinsi")) {
                logger.info("[ExceptionHandlerInterceptor.provinsi] skiping token");
            } else if (namespace.contains("/mobileapi/kota")) {
                logger.info("[ExceptionHandlerInterceptor.kota] skiping token");
            } else if (namespace.contains("/mobileapi/kecamatan")) {
                logger.info("[ExceptionHandlerInterceptor.kecamatan] skiping token");
            } else if (namespace.contains("/mobileapi/desa")) {
                logger.info("[ExceptionHandlerInterceptor.desa] skiping token");
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

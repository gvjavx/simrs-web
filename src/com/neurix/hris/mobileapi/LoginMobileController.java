package com.neurix.hris.mobileapi;

import com.neurix.authorization.role.model.Roles;
import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.LoginMobile;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAwareSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LoginMobileController extends ValidationAwareSupport implements ModelDriven<Object> {

    protected static transient Logger logger = Logger.getLogger(LoginMobileController.class);

    private UserBo userBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private LoginMobile model = new LoginMobile();
    private String id;
    private String tokenFcm;
    private String tokenExpo;
    private String os;
    private String username;
    private String password;
    private String idDevice;

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    // POST /loginmobile
    public HttpHeaders create() {
        logger.info("[TaskAsmudController.create] start process POST /loginmobile >>>");

        List<User> result = new ArrayList<>();

        String userId = username;
        String rawPassword = password;
        boolean finger = false;

        if (idDevice != null && !idDevice.equalsIgnoreCase("")){

            User user = new User();

            try {
                user = userBoProxy.getUserByIdDevice(idDevice);
                userId = user.getUserId();
                finger = true;
            } catch (GeneralBOException e) {
                logger.info("[LoginMobileController.create] error get user data POST /loginmobile >>>");
                throw new GeneralBOException("Fingerprint not found");
            }
        }

        //checking if other device used this userId, if found then throws error
        boolean isFound = false;
        try {
            isFound = userBoProxy.isFoundOtherSessionActiveUserSessionLog(userId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[UserAction.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if (!isFound) { // if not found user using with other device, then checking password and userid

            UserDetailsLogin userDetailsLogin = null;
            try {
                userDetailsLogin = userBoProxy.getMobileUserByUsername(userId,"Y");

            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[UserAction.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[UserAction.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            if (userDetailsLogin != null) {

                //checking encrypted password with input password
                ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
                String hashedPassword = passwordEncoder.encodePassword(rawPassword,null);
                String userPassword = userDetailsLogin.getPassword();

                if (userPassword.equalsIgnoreCase(hashedPassword) || finger) {

                    String sessionId = null;
                    //get session id
                    HttpSession session = ServletActionContext.getRequest().getSession();
                    if (session != null) {
                        sessionId = session.getId();
                        session.setAttribute("tokenId",sessionId);
                    }

                    if (sessionId!=null) {

                        // insert into table session user log

                        try {
                            userBoProxy.insertUserSessionLog(sessionId,userDetailsLogin,"mobile");
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            try {
                                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.insertUserSessionLog");
                            } catch (GeneralBOException e1) {
                                logger.error("[UserAction.insertUserSessionLog] Error when saving error,", e1);
                            }
                            logger.error("[UserAction.insertUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                            throw new GeneralBOException(e);
                        }

                        //add into body is tokenId

                        model.setTokenId(sessionId);
                        model.setUserId(userDetailsLogin.getUserId());
                        model.setUserName(userDetailsLogin.getUserNameDetail());
                        model.setAreaId(userDetailsLogin.getAreaId());
                        model.setAreaName(userDetailsLogin.getAreaName());
                        model.setBranchId(userDetailsLogin.getBranchId());
                        model.setBranchName(userDetailsLogin.getBranchName());
                        model.setPositionId(userDetailsLogin.getPositionId());
                        model.setPositionName(userDetailsLogin.getPositionName());
                        model.setJenisKelamin(userDetailsLogin.getJenisKelamin());
                        model.setPhotoUrl(userDetailsLogin.getPhotoUserUrl());
                        model.setIdPelayanan(userDetailsLogin.getIdPleyanan());
                        model.setIdDevice(userDetailsLogin.getIdDevice());
                        model.setFlagFingerMobile(userDetailsLogin.getFlagFingerMoblie());
                        model.setPin(userDetailsLogin.getPin());

                        String roleId="";
                        String roleName="";
                        List<Roles> listOfRoles = userDetailsLogin.getRoles();
                        for (Roles item : listOfRoles) {
                            roleId = item.getRoleId().toString();
                            roleName = item.getRoleName();
                        }

                        model.setRoleId(roleId);
                        model.setRoleName(roleName);

                        NotifikasiFcm notifikasiFcm = new NotifikasiFcm();
                        notifikasiFcm.setUserId(model.getUserId());
                        notifikasiFcm.setUserName(model.getUserName());
                        notifikasiFcm.setTokenFcm(tokenFcm == null ? "" : tokenFcm);
                        notifikasiFcm.setTokenExpo(tokenExpo == null ? "" : tokenExpo);
                        notifikasiFcm.setOs(os == null ? "" : os);
                        notifikasiFcm.setLastUpdateWho(model.getUserName());
                        notifikasiFcm.setCreatedWho(model.getUserName());
                        try {
                            notifikasiFcmBoProxy.saveAdd(notifikasiFcm);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            try {
                                logId = userBoProxy.saveErrorMessage(e.getMessage(), "LoginMobileController.isFoundOtherSessionActiveUserSessionLog");
                            } catch (GeneralBOException e1) {
                                logger.error("[LoginMobileController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                            }
                            logger.error("[LoginMobileController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                            throw new GeneralBOException(e);
                        }
                    } else {
                        model.setActionMessage("Unable to create token, please call your admin to handle it.");
                        logger.info("Unable to create token, please call your admin to handle it.");
                        throw new GeneralBOException("Unable to create token, please call your admin to handle it.");

                    }

                } else {
                    model.setActionMessage("Incorrect password, please right password or call your admin to handle it.");
                    logger.info("Incorrect password, please right password or call your admin to handle it.");
                    throw new GeneralBOException("Incorrect password, please right password or call your admin to handle it.");

                }

            } else {
                model.setActionMessage("No found this user at database system, please call your admin to handle it.");
                logger.info("No found this user at database system, please call your admin to handle it.");
                throw new GeneralBOException("No found this user at database system, please call your admin to handle it.");

            }

        } else {
            model.setActionMessage("Found this user is still using apps with other device, please call your admin to handle it.");
            logger.info("Found this user is still using apps with other device, please call your admin to handle it.");
            throw new GeneralBOException("Found this user is still using apps with other device, please call your admin to handle it.");
        }

        logger.info("[LoginMobileController.create] end process POST /loginmobile <<<");

        return new DefaultHttpHeaders("success")
                .disableCaching();

    }

    public String datatoken(){
        logger.info("[LoginMobileController.datatoken] start process GET /loginmobile/{id}/datatoken >>>");

        String tokenStatus = null;
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getHeader("tokenId");
        if (token!=null){
            boolean isFound = false;
            try {
                isFound = userBoProxy.isActiveUserSessionLog(token);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.isActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[UserAction.isActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[UserAction.isActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
            model = new LoginMobile();
            model.setActionMessage(String.valueOf(isFound));

        } else {
            throw new GeneralBOException("[ReLogin] No found tokenId. Please re-Login again your apps.");
        }

        logger.info("[NotifPlanController.confirm] end process GET /notifplan/{id} <<<");
        return "succes";
    }

    // GET /loginmobile/{id}/logout
    public String logout() {
        logger.info("[LoginMobileController.logoutUser] start process GET /loginmobile/{id}/logoutUser >>>");

        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getHeader("tokenId");
        if (token!=null) {

            boolean isFound = false;
            try {
                isFound = userBoProxy.isActiveUserSessionLog(token);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.isActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[UserAction.isActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[UserAction.isActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            if (isFound) {

                //update user log session logout

                try {
                    userBoProxy.updateUserSessionLog(token);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.updateUserSessionLog");
                    } catch (GeneralBOException e1) {
                        logger.error("[UserAction.updateUserSessionLog] Error when saving error,", e1);
                    }
                    logger.error("[UserAction.updateUserSessionLog] Error when updating data user session logout by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    throw new GeneralBOException(e);
                }

                model = new LoginMobile();
                model.setActionMessage("Your user ["+ id + "] is logout e-HEALTH mobile successfully.");

                HttpSession session = request.getSession();
                if (session!=null) {
                    session.invalidate();
                }

            } else {
                throw new GeneralBOException("[ReLogin] No found tokenId. Please re-Login again your apps.");
            }

        } else {
            throw new GeneralBOException("[ReLogin] No found tokenId. Please re-Login again your apps.");
        }
        logger.info("[LoginMobileController.logoutUser] end process GET /loginmobile/{id}/logoutUser <<<");
        return "success";
    }

    @Override
    public Object getModel() {
        return model;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTokenFcm(String tokenFcm) {
        this.tokenFcm = tokenFcm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenExpo() {
        return tokenExpo;
    }

    public void setTokenExpo(String tokenExpo) {
        this.tokenExpo = tokenExpo;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }
}

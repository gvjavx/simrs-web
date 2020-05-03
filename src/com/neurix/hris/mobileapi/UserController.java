package com.neurix.hris.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.LoginMobile;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.sql.Timestamp;

/**
 * @author gondok
 * Thursday, 19/03/20 10:40
 */
public class UserController implements ModelDriven<Object> {
    protected static transient Logger logger = Logger.getLogger(LoginMobileController.class);

    private UserBo userBoProxy;
    private LoginMobile model = new LoginMobile();

    private String idDevice;
    private String userId;
    private String username;
    private String action;
    private String newPassword;
    private String oldPassword;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        UserController.logger = logger;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserBo getUserBoProxy() {
        return userBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public void setModel(LoginMobile model) {
        this.model = model;
    }

    public HttpHeaders create() {
        logger.info("[UserController.create] start process POST /user >>>");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        User result = new User();

        if  (action.equalsIgnoreCase("saveIdDevice")) {
            try {
                result = userBoProxy.getUserById(userId, "Y");
            } catch (GeneralBOException e) {
                logger.info("[UserController.create] error get user by id POST /user >>>");
            }

            result.setLastUpdate(now);
            result.setLastUpdateWho(username);
            result.setIdDevice(idDevice);

            try {
                userBoProxy.saveEditIdDevice(result);
                model.setActionMessage("Success");
            } catch (GeneralBOException e) {
                logger.info("[UserController.create] error save edit POST /user >>>");
            }
        }

        if (action.equalsIgnoreCase("resetPassword")) {
            UserDetailsLogin userDetailsLogin = new UserDetailsLogin();
            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
            User user = new User();
            user.setUserId(userId);
            user.setUsername(username);
            user.setLastUpdate(now);
            user.setLastUpdateWho(username);
            user.setAction("U");


            try {
                userDetailsLogin = userBoProxy.getMobileUserByUsername(userId, "Y");
            } catch (GeneralBOException e) {
                logger.info("[UserController.create] error get user by id POST /user >>>");
            }

            String encodedNewPassword = passwordEncoder.encodePassword(newPassword, null);
            String encodedOldPassword = passwordEncoder.encodePassword(oldPassword, null);
            user.setPassword(encodedNewPassword);

            if (encodedOldPassword.equalsIgnoreCase(userDetailsLogin.getPassword())) {
                try {
                    userBoProxy.saveEditPassword(user);
                    model.setActionMessage("Success");
                } catch (GeneralBOException e){
                    logger.info("[UserController.create] error get user by id POST /user >>>");
                }
            } else model.setActionMessage("Password Lama Salah");

        }


        logger.info("[UserController.create] end process POST /user >>>");
        return new DefaultHttpHeaders("success")
                .disableCaching();
    }

    @Override
    public Object getModel() {
        return model;
    }
}

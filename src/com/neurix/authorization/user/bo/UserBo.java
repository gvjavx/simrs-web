package com.neurix.authorization.user.bo;


import com.neurix.authorization.user.model.*;
import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 14/01/13
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
public interface UserBo extends GeneralBo {

    public User getUserById(String userId, String flag) throws GeneralBOException;
    public User getUserByIdDevice(String idDevice) throws GeneralBOException;
    public List<User> getByCriteria(User searchUser) throws GeneralBOException;
    public List<User> getUserSameBranchByCriteria(User searchUsers) throws GeneralBOException;
    public List<User> getUserSamePositionByCriteria(User searchUsers) throws GeneralBOException;
    public List<User> getComboOpsGpsOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException;
    public List<User> getComboAsmudOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException;
    public List<User> getComboAsmanOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException;
    public List<User> getComboMantanOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException;
    public List<User> getComboAsmanWithCriteria(String query) throws GeneralBOException;
    public List<User> getComboAsmudWithCriteria(String query) throws GeneralBOException;

    public boolean isActiveUserSessionLog(String sessionId) throws HibernateException;
    public boolean isFoundOtherSessionActiveUserSessionLog(String userId) throws HibernateException;
    public UserDetailsLogin getMobileUserByUsername(String username, String active) throws HibernateException,UsernameNotFoundException;

    public List<User> getComboApprovalPersonWithCriteria(String query) throws GeneralBOException;
    public List<User> getComboTanamanPersonWithCriteria(String query) throws GeneralBOException;
    public User saveAdd(User newUser) throws GeneralBOException;
    public void saveDelete(User deleteUser) throws GeneralBOException;
    public void saveEdit(User editUser) throws GeneralBOException;
    public void saveEditIdDevice(User user) throws GeneralBOException;
    public void saveEditPassword(User usersUpdated) throws GeneralBOException;
    public List<User> getComboUserWithCriteria(String query, String Branch, String Divisi) throws GeneralBOException;
    public List<User> getDataUser(String query) throws GeneralBOException;
    public List<User> getComboUser(String query) throws GeneralBOException;
    public List<User> getComboUserIdWithCriteria(String query) throws GeneralBOException;

    public List<ErrorLog> getErrorLogByCriteria(ErrorLog searchErrorLog) throws GeneralBOException;
    public List<UserSessionLog> getUserSessionLogByCriteria(UserSessionLog searchUserSessionLog) throws GeneralBOException;
    public List<UserSessionLog> getUserSessionLogCount(UserSessionLog searchUserSessionLog) throws GeneralBOException;

    public UserDetailsLogin getUserByUsername(String username, String active, String contextPath) throws HibernateException,UsernameNotFoundException;
    public void updateUserSessionLog(String sessionid) throws HibernateException;
    public void insertUserSessionLog(String sessionId, UserDetailsLogin userDetailsLogin, String ip) throws HibernateException;
    public UserDetailsLogin retrievePhotoUser(String path, String username) throws HibernateException,IOException;
    public List getMenuUser(UserDetailsLogin userDetailsLogin, String contextPath) throws HibernateException;
    public List<User> getUserLLByCriteria(User searchUsers) throws GeneralBOException;
}

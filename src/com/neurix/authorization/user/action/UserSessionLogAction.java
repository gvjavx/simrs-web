package com.neurix.authorization.user.action;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.UserSessionLog;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.security.CustomHttpSessionEventPublisher;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.xml.ws.spi.http.HttpContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Ferdi on 16/02/2015.
 */
public class UserSessionLogAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(UserSessionLogAction.class);

    boolean isSearch = true;

    private String userName ;
    private String stLoginTimestampFrom ;
    private String stLoginTimestampTo;
    private String flag ;

    private String dateFrom = "";
    private String dateTo = "";
    private UserBo userBoProxy;
    private List<UserSessionLog> userSessionLogList;
    private UserSessionLog userSessionLog;

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStLoginTimestampTo() {
        return stLoginTimestampTo;
    }

    public void setStLoginTimestampTo(String stLoginTimestampTo) {
        this.stLoginTimestampTo = stLoginTimestampTo;
    }

    public String getStLoginTimestampFrom() {
        return stLoginTimestampFrom;
    }

    public void setStLoginTimestampFrom(String stLoginTimestampFrom) {
        this.stLoginTimestampFrom = stLoginTimestampFrom;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public List<UserSessionLog> getUserSessionLogList() {
        return userSessionLogList;
    }

    public void setUserSessionLogList(List<UserSessionLog> userSessionLogList) {
        this.userSessionLogList = userSessionLogList;
    }

    public UserSessionLog getUserSessionLog() {
        return userSessionLog;
    }

    public void setUserSessionLog(UserSessionLog userSessionLog) {
        this.userSessionLog = userSessionLog;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {

        logger.info("[UserSessionLogAction.view] start process >>>");

        String itemId = getId();
        UserSessionLog editUserSessionLog = new UserSessionLog();

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<UserSessionLog> listOfResult = (List) session.getAttribute("listOfResult");

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (UserSessionLog userSessionLogOfList : listOfResult) {
                    if (id.equalsIgnoreCase(userSessionLogOfList.getStId())) {
                        setUserSessionLog(userSessionLogOfList);
                        break;
                    }
                }

            } else {
                setUserSessionLog(new UserSessionLog());
            }
        }

        logger.info("[UserSessionLogAction.view] end process <<<");

        return "kill_session";
    }

    @Override
    public String save() {

        logger.info("[UserSessionLogAction.save] start process >>>");

        //kill session

        UserSessionLog killUserSession = getUserSessionLog();

        boolean isFound = false;
        HttpSession sessionItem = CustomHttpSessionEventPublisher.find(killUserSession.getSessionId());
        if (sessionItem!=null) {
            sessionItem.invalidate();
            isFound = true;
        }

        if (isFound) {

            try {

                userBoProxy.updateUserSessionLog(killUserSession.getSessionId());

            } catch (UsernameNotFoundException e) {
                logger.error("[UserSessionLogAction.save] Error when killing user session,", e);
                addActionError("Error, " + e.getMessage());
                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.updateUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[UserSessionLogAction.save] Error when saving error,", e1);
                }
                logger.error("[UserSessionLogAction.save] Error when killing user session," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

        } else {
            logger.error("[UserSessionLogAction.save] Error when killing user session, Found problem when updating user session, cause no found this session active, please inform to your admin.");
            addActionError("Error, Found problem when updating user session, cause no found this session active, please inform to your admin.\n");
            return ERROR;
        }


        logger.info("[UserSessionLogAction.save] end process <<<");

        return "success_save";

    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        UserSessionLog initUserSessionLog = new UserSessionLog();
        setUserSessionLog(initUserSessionLog);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        return INPUT;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[UserSessionLogAction.search] start process >>>");

        UserSessionLog searchUserSessionLog = getUserSessionLog();

        if (searchUserSessionLog.getStLoginTimestampFrom() != null && !"".equalsIgnoreCase(searchUserSessionLog.getStLoginTimestampFrom())) {
            searchUserSessionLog.setLoginTimestampFrom(CommonUtil.convertToTimestamp(searchUserSessionLog.getStLoginTimestampFrom()));
            if (searchUserSessionLog.getStLoginTimestampTo() != null && !"".equalsIgnoreCase(searchUserSessionLog.getStLoginTimestampTo())) {
                searchUserSessionLog.setLoginTimestampTo(CommonUtil.convertToTimestamp(searchUserSessionLog.getStLoginTimestampTo()));
            } else {
                searchUserSessionLog.setLoginTimestampTo(searchUserSessionLog.getLoginTimestampFrom());
            }

        } else {
            if (searchUserSessionLog.getStLoginTimestampTo() != null && !"".equalsIgnoreCase(searchUserSessionLog.getStLoginTimestampTo())) {
                searchUserSessionLog.setLoginTimestampTo(CommonUtil.convertToTimestamp(searchUserSessionLog.getStLoginTimestampTo()));
                searchUserSessionLog.setLoginTimestampFrom(searchUserSessionLog.getLoginTimestampTo());
            }
        }

        List<UserSessionLog> listOfSearchUserSessionLog = new ArrayList();
        try {
            listOfSearchUserSessionLog = userBoProxy.getUserSessionLogByCriteria(searchUserSessionLog);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.getUserSessionLogByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserSessionLogAction.search] Error when saving error,", e1);
            }
            logger.error("[UserSessionLogAction.search] Error when searching user session log by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchUserSessionLog);

        logger.info("[UserSessionLogAction.search] end process <<<");
        setSearch(true);
        return SUCCESS;
    }

    public String searchCount() {
        logger.info("[UserSessionLogAction.search] start process >>>");

        UserSessionLog searchUserSessionLog = getUserSessionLog();

        if(searchUserSessionLog == null){
            searchUserSessionLog = new UserSessionLog();
            searchUserSessionLog.setUserName(getUserName());
            searchUserSessionLog.setUserName(getUserName());
            searchUserSessionLog.setStLoginTimestampFrom(getStLoginTimestampFrom());
            searchUserSessionLog.setStLoginTimestampTo(getStLoginTimestampTo());
            searchUserSessionLog.setFlag(getFlag());
        }

        setUserSessionLog(searchUserSessionLog);

        if (searchUserSessionLog.getStLoginTimestampFrom() != null && !"".equalsIgnoreCase(searchUserSessionLog.getStLoginTimestampFrom())) {
            searchUserSessionLog.setLoginTimestampFrom(CommonUtil.convertToTimestamp(searchUserSessionLog.getStLoginTimestampFrom()));
            if (searchUserSessionLog.getStLoginTimestampTo() != null && !"".equalsIgnoreCase(searchUserSessionLog.getStLoginTimestampTo())) {
                searchUserSessionLog.setLoginTimestampTo(CommonUtil.convertToTimestamp(searchUserSessionLog.getStLoginTimestampTo()));
            } else {
                searchUserSessionLog.setLoginTimestampTo(searchUserSessionLog.getLoginTimestampFrom());
            }

        } else {
            if (searchUserSessionLog.getStLoginTimestampTo() != null && !"".equalsIgnoreCase(searchUserSessionLog.getStLoginTimestampTo())) {
                searchUserSessionLog.setLoginTimestampTo(CommonUtil.convertToTimestamp(searchUserSessionLog.getStLoginTimestampTo()));
                searchUserSessionLog.setLoginTimestampFrom(searchUserSessionLog.getLoginTimestampTo());
            }
        }

        List<UserSessionLog> listOfSearchUserSessionLog = new ArrayList();
        try {
            listOfSearchUserSessionLog = userBoProxy.getUserSessionLogCount(searchUserSessionLog);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.getUserSessionLogByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserSessionLogAction.search] Error when saving error,", e1);
            }
            logger.error("[UserSessionLogAction.search] Error when searching user session log by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCount");
        session.setAttribute("listOfResultCount", listOfSearchUserSessionLog);

        logger.info("[UserSessionLogAction.search] end process <<<");
        setSearch(false);
        return SUCCESS;
    }

}

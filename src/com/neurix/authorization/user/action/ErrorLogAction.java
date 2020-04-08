package com.neurix.authorization.user.action;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.ErrorLog;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferdi on 16/02/2015.
 */
public class ErrorLogAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(ErrorLogAction.class);

    private UserBo userBoProxy;
    private List<ErrorLog> errorLogList;
    private ErrorLog errorLog;

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public List<ErrorLog> getErrorLogList() {
        return errorLogList;
    }

    public void setErrorLogList(List<ErrorLog> errorLogList) {
        this.errorLogList = errorLogList;
    }

    public ErrorLog getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(ErrorLog errorLog) {
        this.errorLog = errorLog;
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
        logger.info("[ErrorLogAction.view] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ErrorLog> listOfResult = (List) session.getAttribute("listOfResult");

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (ErrorLog errorLogOfList : listOfResult) {
                    if (id.equalsIgnoreCase(errorLogOfList.getErrorId())) {
                        setErrorLog(errorLogOfList);
                        break;
                    }
                }
            } else {
                setErrorLog(new ErrorLog());
            }
        } else {
            setErrorLog(new ErrorLog());
        }

        logger.info("[ErrorLogAction.view] end process <<<");

        return "view_detail";
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        ErrorLog initErrorLog = new ErrorLog();
        setErrorLog(initErrorLog);
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
        logger.info("[ErrorLogAction.search] start process >>>");

        ErrorLog searchErrorLog = getErrorLog();

        if (searchErrorLog.getStErrorTimestampFrom() != null && !"".equalsIgnoreCase(searchErrorLog.getStErrorTimestampFrom())) {
            searchErrorLog.setErrorTimestampFrom(CommonUtil.convertToTimestamp(searchErrorLog.getStErrorTimestampFrom()));
            if (searchErrorLog.getStErrorTimestampTo() != null && !"".equalsIgnoreCase(searchErrorLog.getStErrorTimestampTo())) {
                searchErrorLog.setErrorTimestampTo(CommonUtil.convertToTimestamp(searchErrorLog.getStErrorTimestampTo()));
            } else {
                searchErrorLog.setErrorTimestampTo(searchErrorLog.getErrorTimestampFrom());
            }

        } else {
            if (searchErrorLog.getStErrorTimestampTo() != null && !"".equalsIgnoreCase(searchErrorLog.getStErrorTimestampTo())) {
                searchErrorLog.setErrorTimestampTo(CommonUtil.convertToTimestamp(searchErrorLog.getStErrorTimestampTo()));
                searchErrorLog.setErrorTimestampFrom(searchErrorLog.getErrorTimestampTo());
            }
        }

        List<ErrorLog> listOfSearchErrorLog = new ArrayList();
        try {
            listOfSearchErrorLog = userBoProxy.getErrorLogByCriteria(searchErrorLog);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.getErrorLogByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ErrorLogAction.search] Error when saving error,", e1);
            }
            logger.error("[ErrorLogAction.search] Error when searching error log by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchErrorLog);

        logger.info("[ErrorLogAction.search] end process <<<");

        return SUCCESS;
    }



}

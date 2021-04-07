package com.neurix.simrs.transaksi.logtransaction.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.logtransaction.bo.LogTransactionBo;
import com.neurix.simrs.transaksi.logtransaction.model.LogTransaction;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogTransactionAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(LogTransactionAction.class);
    private LogTransactionBo logTransactionBoProxy;
    private LogTransaction logTransaction;

    public static Logger getLogger() {
        return logger;
    }

    public void setLogTransactionBoProxy(LogTransactionBo logTransactionBoProxy) {
        this.logTransactionBoProxy = logTransactionBoProxy;
    }

    public LogTransactionBo getLogTransactionBoProxy() {
        return logTransactionBoProxy;
    }

    public LogTransaction getLogTransaction() {
        return logTransaction;
    }

    public void setLogTransaction(LogTransaction logTransaction) {
        this.logTransaction = logTransaction;
    }

    public LogTransaction init(BigInteger id, String flag){
        logger.info("[LogTransactionAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<LogTransaction> listOfResult = (List<LogTransaction>) session.getAttribute("listOfResult");

        if(id != null){
            if(listOfResult != null){
                for (LogTransaction logTrx : listOfResult) {
                    if(id == logTrx.getPgLogTrxId() && flag.equalsIgnoreCase(logTrx.getFlag())){
                        setLogTransaction(logTrx);
                        break;
                    }
                }
            } else {
                setLogTransaction(new LogTransaction());
            }

            logger.info("[LogTransactionAction.init] end process >>>");
        }
        return getLogTransaction();
    }

    public String saveDelete(){
        logger.info("[LogTransactionAction.saveDelete] start process >>>");
        try {

            LogTransaction deleteLogTrx = getLogTransaction();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLogTrx.setLastUpdate(updateTime);
            deleteLogTrx.setLastUpdateWho(userLogin);
            deleteLogTrx.setAction("U");
            deleteLogTrx.setFlag("N");

            logTransactionBoProxy.saveDelete(deleteLogTrx);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logTransactionBoProxy.saveErrorMessage(e.getMessage(), "LogTransactionBo.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LogTransactionAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogTransactionAction.saveDelete] Error when editing item," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LogTransactionAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        logger.info("[LogTransactionAction.delete] start process >>>");

        BigInteger itemId = new BigInteger(getId());
        String itemFlag = getFlag();
        LogTransaction deleteLogTrx = new LogTransaction();

        if (itemFlag != null ) {

            try {
                deleteLogTrx = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = logTransactionBoProxy.saveErrorMessage(e.getMessage(), "LogTransactionBo.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[LogTransactionAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LogTransactionAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLogTrx != null) {
                setLogTransaction(deleteLogTrx);
            } else {
                deleteLogTrx.setPgLogTrxId(itemId);
                deleteLogTrx.setFlag(itemFlag);
                setLogTransaction(deleteLogTrx);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteLogTrx.setPgLogTrxId(itemId);
            deleteLogTrx.setFlag(itemFlag);
            setLogTransaction(deleteLogTrx);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        setDelete(true);
        logger.info("[LogTransactionAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[LogTransactionAction.search] start process >>>");

        LogTransaction searchLogTrx = getLogTransaction();
        List<LogTransaction> listOfsearchLogTrx = new ArrayList();

        try {
            listOfsearchLogTrx = logTransactionBoProxy.getByCriteria(searchLogTrx);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logTransactionBoProxy.saveErrorMessage(e.getMessage(), "LogTransactionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LogTransactionAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogTransactionAction.save] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLogTrx);

        logger.info("[LogTransactionAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[LogTransactionAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LogTransactionAction.initForm] end process >>>");
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

    public String pagingLogTransaction() {
        return SUCCESS;
    }
}

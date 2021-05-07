package com.neurix.akuntansi.transaksi.billingSystem.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.akuntansi.transaksi.billingSystem.bo.LogTrxBo;
import com.neurix.akuntansi.transaksi.billingSystem.model.LogTransaction;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogTrxAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(LogTrxAction.class);
    private LogTrxBo logTrxBoProxy;
    private LogTransaction logTransaction;

    public static Logger getLogger() {
        return logger;
    }

    public void setLogTrxBoProxy(LogTrxBo logTrxBoProxy) {
        this.logTrxBoProxy = logTrxBoProxy;
    }

    public LogTrxBo getLogTrxBoProxy() {
        return logTrxBoProxy;
    }

    public LogTransaction getLogTransaction() {
        return logTransaction;
    }

    public void setLogTransaction(LogTransaction logTransaction) {
        this.logTransaction = logTransaction;
    }

    public LogTransaction init(BigInteger id, String flag){
        logger.info("[LogTrxAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<LogTransaction> listOfResult = (List<LogTransaction>) session.getAttribute("listOfResult");

        if(id != null){
            if(listOfResult != null){
                for (LogTransaction logTrx : listOfResult) {
                    if(id.equals(logTrx.getPgLogTrxId()) == true && flag.equalsIgnoreCase(logTrx.getFlag())){
                        setLogTransaction(logTrx);
                        break;
                    }
                }
            } else {
                setLogTransaction(new LogTransaction());
            }

            logger.info("[LogTrxAction.init] end process >>>");
        }
        return getLogTransaction();
    }

    public String saveDelete(){
        logger.info("[LogTrxAction.saveDelete] start process >>>");
        try {

            LogTransaction deleteLogTrx = getLogTransaction();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLogTrx.setLastUpdate(updateTime);
            deleteLogTrx.setLastUpdateWho(userLogin);
            deleteLogTrx.setAction("U");
            deleteLogTrx.setFlag("N");

            logTrxBoProxy.saveDelete(deleteLogTrx);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logTrxBoProxy.saveErrorMessage(e.getMessage(), "LogTrxBo.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LogTrxAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogTrxAction.saveDelete] Error when editing item," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LogTrxAction.saveDelete] end process <<<");

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
        logger.info("[LogTrxAction.delete] start process >>>");

        BigInteger itemId = new BigInteger(getId());
        String itemFlag = getFlag();
        LogTransaction deleteLogTrx = new LogTransaction();

        if (itemFlag != null ) {

            try {
                deleteLogTrx = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = logTrxBoProxy.saveErrorMessage(e.getMessage(), "LogTrxBo.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[LogTrxAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LogTrxAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLogTrx != null) {
                if(deleteLogTrx.getReceivedDate() != null) {
                    deleteLogTrx.setStReceivedDate(CommonUtil.convertTimestampToStringLengkap(deleteLogTrx.getReceivedDate()));
                }
                if(deleteLogTrx.getSentDate() != null) {
                    deleteLogTrx.setStSentDate(CommonUtil.convertTimestampToStringLengkap(deleteLogTrx.getSentDate()));
                }
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
        logger.info("[LogTrxAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[LogTrxAction.view] start process >>>");

        BigInteger itemId = new BigInteger(getId());
        String itemFlag = getFlag();
        LogTransaction viewLogTrx = new LogTransaction();

        if (itemFlag != null ) {
            try {
                viewLogTrx = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = logTrxBoProxy.saveErrorMessage(e.getMessage(), "LogTrxBo.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[LogTrxAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LogTrxAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewLogTrx != null) {
                if(viewLogTrx.getReceivedDate() != null) {
                    viewLogTrx.setStReceivedDate(CommonUtil.convertTimestampToStringLengkap(viewLogTrx.getReceivedDate()));
                }
                if(viewLogTrx.getSentDate() != null) {
                    viewLogTrx.setStSentDate(CommonUtil.convertTimestampToStringLengkap(viewLogTrx.getSentDate()));
                }
                setLogTransaction(viewLogTrx);
            } else {
                viewLogTrx.setPgLogTrxId(itemId);
                viewLogTrx.setFlag(itemFlag);
                setLogTransaction(viewLogTrx);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewLogTrx.setPgLogTrxId(itemId);
            viewLogTrx.setFlag(itemFlag);
            setLogTransaction(viewLogTrx);
            addActionError("Error, Unable to find data.");
            return "failure";
        }
        setDelete(true);
        logger.info("[LogTrxAction.view] end process <<<");

        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[LogTrxAction.search] start process >>>");

        LogTransaction searchLogTrx = getLogTransaction();
        List<LogTransaction> listOfsearchLogTrx = new ArrayList();

        try {
            listOfsearchLogTrx = logTrxBoProxy.getByCriteria(searchLogTrx);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logTrxBoProxy.saveErrorMessage(e.getMessage(), "LogTransactionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LogTrxAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogTrxAction.save] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLogTrx);

        logger.info("[LogTrxAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[LogTrxAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LogTrxAction.initForm] end process >>>");
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

package com.neurix.hris.transaksi.logcron.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.logcron.bo.LogCronBo;
import com.neurix.hris.transaksi.logcron.model.LogCron;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogCronAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(LogCronAction.class);
    private LogCronBo logCronBoProxy;
    private LogCron logCron;


    public LogCronBo getLogCronBoProxy() {
        return logCronBoProxy;
    }

    public void setLogCronBoProxy(LogCronBo logCronBoProxy) {
        this.logCronBoProxy = logCronBoProxy;
    }

    public LogCron getLogCron() {
        return logCron;
    }

    public void setLogCron(LogCron logCron) {
        this.logCron = logCron;
    }
    
    
    public LogCron init(String id, String flag){
        logger.info("[LogCronAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<LogCron> listOfResult = (List<LogCron>) session.getAttribute("listOfResult");

        if(id != null && !"".equalsIgnoreCase(id)){
            if(listOfResult != null){
                for (LogCron logCron : listOfResult) {
                    if(id.equalsIgnoreCase(logCron.getLogCronId()) && flag.equalsIgnoreCase(logCron.getFlag())){
                        setLogCron(logCron);
                        break;
                    }
                }
            } else {
                setLogCron(new LogCron());
            }

            logger.info("[LogCronAction.init] end process >>>");
        }
        return getLogCron();
    }

    public String saveAdd(){
        logger.info("[LogCronAction.saveAdd] start process >>>");

        try {
            LogCron logCron = getLogCron();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            logCron.setCreatedWho(userLogin);
            logCron.setLastUpdate(updateTime);
            logCron.setCreatedDate(updateTime);
            logCron.setLastUpdateWho(userLogin);
            logCron.setAction("C");
            logCron.setFlag("Y");

            logCronBoProxy.saveAdd(logCron);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "LogCronBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[LogCronAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogCronAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[LogCronAction.saveAdd] end process >>>");
        return "success_save_add";
    }
    
    public String saveDelete(){
        logger.info("[LogCronAction.saveDelete] start process >>>");
        try {

            LogCron deleteLogCron = getLogCron();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLogCron.setLastUpdate(updateTime);
            deleteLogCron.setLastUpdateWho(userLogin);
            deleteLogCron.setAction("U");
            deleteLogCron.setFlag("N");

            logCronBoProxy.saveDelete(deleteLogCron);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LogCronAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogCronAction.saveDelete] Error when editing item," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LogCronAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
    
    @Override
    public String add() {
        logger.info("[LogCronAction.add] start process >>>");
        LogCron addLogCron = new LogCron();
        setLogCron(addLogCron);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[LogCronAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[LogCronAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        LogCron editLogCron = new LogCron();

        if(itemFlag != null){
            try {
                editLogCron = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[LogCronAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[LogCronAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editLogCron != null) {
                setLogCron(editLogCron);
            } else {
                editLogCron.setFlag(itemFlag);
                editLogCron.setLogCronId(itemId);
                setLogCron(editLogCron);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editLogCron.setLogCronId(itemId);
            editLogCron.setFlag(getFlag());
            setLogCron(editLogCron);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[LogCronAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[LogCronAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        LogCron deleteLogCron = new LogCron();

        if (itemFlag != null ) {

            try {
                deleteLogCron = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "LogCronBO.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[LogCronAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LogCronAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLogCron != null) {
                setLogCron(deleteLogCron);
            } else {
                deleteLogCron.setLogCronId(itemId);
                deleteLogCron.setFlag(itemFlag);
                setLogCron(deleteLogCron);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteLogCron.setLogCronId(itemId);
            deleteLogCron.setFlag(itemFlag);
            setLogCron(deleteLogCron);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        setDelete(true);
        logger.info("[LogCronAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[LogCronAction.add] start process >>>");
        LogCron addLogCron = new LogCron();
        setLogCron(addLogCron);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[LogCronAction.add] stop process >>>");
        return "init_add_user";
    }

    @Override
    public String save() {
        logger.info("[LogCronAction.save] start process >>>");
        if (isAddOrEdit()) {

            if (!isAdd()) {
                logger.info("[LogCronAction.save] start process >>>");
                try {

                    LogCron editLogCron = getLogCron();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    editLogCron.setLastUpdateWho(userLogin);
                    editLogCron.setLastUpdate(updateTime);
                    editLogCron.setAction("U");
                    editLogCron.setFlag("Y");

                    logCronBoProxy.saveEdit(editLogCron);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[LogCronAction.save] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[LogCronAction.save] Error when editing item," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

                logger.info("[LogCronAction.save] end process <<<");

                return "success_save_edit";
            } else {
                //add
                try {
                    LogCron notifikasi = getLogCron();
                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    logCronBoProxy.saveAdd(notifikasi);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "logCronBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[LogCronAction.save] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[LogCronAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }


                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");

                logger.info("[LogCronAction.save] end process >>>");
                return "success_save_add";
            }

        } else if (isDelete()) {

            logger.info("[LogCronAction.save] start process >>>");
            try {

                LogCron deleteLogCron = getLogCron();

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteLogCron.setLastUpdate(updateTime);
                deleteLogCron.setLastUpdateWho(userLogin);
                deleteLogCron.setAction("U");
                deleteLogCron.setFlag("N");

                logCronBoProxy.saveDelete(deleteLogCron);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[LogCronAction.save] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[LogCronAction.save] Error when editing item," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            logger.info("[LogCronAction.save] end process <<<");

            return SUCCESS;

        }

        return null;
    }

    @Override
    public String search() {
        logger.info("[LogCronAction.search] start process >>>");

        LogCron searchLogCron = getLogCron();
        List<LogCron> listOfsearchLogCron = new ArrayList();

        try {
            listOfsearchLogCron = logCronBoProxy.getByCriteria(searchLogCron);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = logCronBoProxy.saveErrorMessage(e.getMessage(), "LogCronBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LogCronAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LogCronAction.save] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLogCron);

        logger.info("[LogCronAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[LogCronAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LogCronAction.initForm] end process >>>");
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

    public String pagingLogCron(){
        return SUCCESS;
    }
}

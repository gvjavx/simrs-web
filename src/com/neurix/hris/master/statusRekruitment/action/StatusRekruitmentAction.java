package com.neurix.hris.master.statusRekruitment.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.statusRekruitment.bo.StatusRekruitmentBo;
import com.neurix.hris.master.statusRekruitment.model.StatusRekruitment;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class StatusRekruitmentAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(StatusRekruitmentAction.class);
    private StatusRekruitmentBo statusRekruitmentBoProxy;
    private StatusRekruitment statusRekruitment;

    public StatusRekruitmentBo getStatusRekruitmentBoProxy() {
        return statusRekruitmentBoProxy;
    }

    public void setStatusRekruitmentBoProxy(StatusRekruitmentBo statusRekruitmentBoProxy) {
        this.statusRekruitmentBoProxy = statusRekruitmentBoProxy;
    }

    public StatusRekruitment getStatusRekruitment() {
        return statusRekruitment;
    }

    public void setStatusRekruitment(StatusRekruitment statusRekruitment) {
        this.statusRekruitment = statusRekruitment;
    }

    private List<StatusRekruitment> initComboStatusRekruitment;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StatusRekruitmentAction.logger = logger;
    }


    public List<StatusRekruitment> getInitComboStatusRekruitment() {
        return initComboStatusRekruitment;
    }

    public void setInitComboStatusRekruitment(List<StatusRekruitment> initComboStatusRekruitment) {
        this.initComboStatusRekruitment = initComboStatusRekruitment;
    }

    public StatusRekruitment init(String kode, String flag){
        logger.info("[StatusRekruitmentAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<StatusRekruitment> listOfResult = (List<StatusRekruitment>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (StatusRekruitment statusRekruitment: listOfResult) {
                    if(kode.equalsIgnoreCase(statusRekruitment.getStatusRekruitmentId().toString()) && flag.equalsIgnoreCase(statusRekruitment.getFlag())){
                        setStatusRekruitment(statusRekruitment);
                        break;
                    }
                }
            } else {
                setStatusRekruitment(new StatusRekruitment());
            }

            logger.info("[StatusRekruitmentAction.init] end process >>>");
        }
        return getStatusRekruitment();
    }

    @Override
    public String add() {
        logger.info("[StatusRekruitmentAction.add] start process >>>");
        StatusRekruitment addStatusRekruitment = new StatusRekruitment();
        setStatusRekruitment(addStatusRekruitment);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[StatusRekruitmentAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[StatusRekruitmentAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        StatusRekruitment editStatusRekruitment = new StatusRekruitment();

        if(itemFlag != null){
            try {
                editStatusRekruitment = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "StatusRekruitmentBO.getStatusRekruitmentByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusRekruitmentAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[StatusRekruitmentAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editStatusRekruitment != null) {
                setStatusRekruitment(editStatusRekruitment);
            } else {
                editStatusRekruitment.setFlag(itemFlag);
                editStatusRekruitment.setStatusRekruitmentId(BigInteger.valueOf(Long.parseLong(itemId)));
                setStatusRekruitment(editStatusRekruitment);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editStatusRekruitment.setStatusRekruitmentId(BigInteger.valueOf(Long.parseLong(itemId)));
            editStatusRekruitment.setFlag(getFlag());
            setStatusRekruitment(editStatusRekruitment);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[StatusRekruitmentAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[StatusRekruitmentAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StatusRekruitment deleteStatusRekruitment = new StatusRekruitment();

        if (itemFlag != null ) {

            try {
                deleteStatusRekruitment = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "StatusRekruitmentBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusRekruitmentAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[StatusRekruitmentAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStatusRekruitment != null) {
                setStatusRekruitment(deleteStatusRekruitment);

            } else {
                deleteStatusRekruitment.setStatusRekruitmentId(BigInteger.valueOf(Long.parseLong(itemId)));
                deleteStatusRekruitment.setFlag(itemFlag);
                setStatusRekruitment(deleteStatusRekruitment);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStatusRekruitment.setStatusRekruitmentId(BigInteger.valueOf(Long.parseLong(itemId)));
            deleteStatusRekruitment.setFlag(itemFlag);
            setStatusRekruitment(deleteStatusRekruitment);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[StatusRekruitmentAction.delete] end process <<<");

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

    public String saveEdit(){
        logger.info("[StatusRekruitmentAction.saveEdit] start process >>>");
        try {

            StatusRekruitment editStatusRekruitment = getStatusRekruitment();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editStatusRekruitment.setLastUpdateWho(userLogin);
            editStatusRekruitment.setLastUpdate(updateTime);
            editStatusRekruitment.setAction("U");
            editStatusRekruitment.setFlag("Y");

            statusRekruitmentBoProxy.saveEdit(editStatusRekruitment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "StatusRekruitmentBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StatusRekruitmentAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StatusRekruitmentAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[StatusRekruitmentAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[StatusRekruitmentAction.saveDelete] start process >>>");
        try {

            StatusRekruitment deleteStatusRekruitment = getStatusRekruitment();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteStatusRekruitment.setLastUpdate(updateTime);
            deleteStatusRekruitment.setLastUpdateWho(userLogin);
            deleteStatusRekruitment.setAction("U");
            deleteStatusRekruitment.setFlag("N");

            statusRekruitmentBoProxy.saveDelete(deleteStatusRekruitment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "StatusRekruitmentBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[StatusRekruitmentAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StatusRekruitmentAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[StatusRekruitmentAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[StatusRekruitmentAction.saveAdd] start process >>>");

        try {
            StatusRekruitment statusRekruitment = getStatusRekruitment();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            statusRekruitment.setCreatedWho(userLogin);
            statusRekruitment.setLastUpdate(updateTime);
            statusRekruitment.setCreatedDate(updateTime);
            statusRekruitment.setLastUpdateWho(userLogin);
            statusRekruitment.setAction("C");
            statusRekruitment.setFlag("Y");

            statusRekruitmentBoProxy.saveAdd(statusRekruitment);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[StatusRekruitmentAction.search] start process >>>");

        StatusRekruitment searchStatusRekruitment = getStatusRekruitment();
        List<StatusRekruitment> listOfsearchStatusRekruitment = new ArrayList();

        try {
            listOfsearchStatusRekruitment = statusRekruitmentBoProxy.getByCriteria(searchStatusRekruitment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "StatusRekruitmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StatusRekruitmentAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StatusRekruitmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchStatusRekruitment);

        logger.info("[StatusRekruitmentAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[StatusRekruitmentAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[StatusRekruitmentAction.initForm] end process >>>");
        return INPUT;
    }

    public String initStatusRekruitment() {
        logger.info("[StatusRekruitmentAction.search] start process >>>");

        StatusRekruitment searchStatusRekruitment = new StatusRekruitment();
        searchStatusRekruitment.setFlag("Y");
        List<StatusRekruitment> listOfsearchStatusRekruitment = new ArrayList();

        try {
            listOfsearchStatusRekruitment = statusRekruitmentBoProxy.getByCriteria(searchStatusRekruitment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "StatusRekruitmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StatusRekruitmentAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StatusRekruitmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultStatusRekruitment");
        session.setAttribute("listOfResultStatusRekruitment", listOfsearchStatusRekruitment);

        logger.info("[StatusRekruitmentAction.search] end process <<<");

        return "";
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}

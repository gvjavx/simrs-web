package com.neurix.hris.master.statusAbsensi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.statusAbsensi.bo.StatusAbsensiBo;
import com.neurix.hris.master.statusAbsensi.model.StatusAbsensi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class StatusAbsensiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(StatusAbsensiAction.class);
    private StatusAbsensiBo statusAbsensiBoProxy;
    private StatusAbsensi statusAbsensi;
    private List<StatusAbsensi> listOfComboStatusAbsensi = new ArrayList<StatusAbsensi>();

    public List<StatusAbsensi> getListOfComboStatusAbsensi() {
        return listOfComboStatusAbsensi;
    }

    public void setListOfComboStatusAbsensi(List<StatusAbsensi> listOfComboStatusAbsensi) {
        this.listOfComboStatusAbsensi = listOfComboStatusAbsensi;
    }


    public StatusAbsensiBo getStatusAbsensiBoProxy() {
        return statusAbsensiBoProxy;
    }

    public void setStatusAbsensiBoProxy(StatusAbsensiBo statusAbsensiBoProxy) {
        this.statusAbsensiBoProxy = statusAbsensiBoProxy;
    }

    public StatusAbsensi getStatusAbsensi() {
        return statusAbsensi;
    }

    public void setStatusAbsensi(StatusAbsensi statusAbsensi) {
        this.statusAbsensi = statusAbsensi;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StatusAbsensiAction.logger = logger;
    }


    public String initComboStatusAbsensi() {
        logger.info("[StatusAbsensiAction.initComboStatusAbsensi] start process >>>");

        StatusAbsensi search = new StatusAbsensi();
        List<StatusAbsensi> statusAbsensiList = new ArrayList();
        search.setFlag("Y");
        try {
            statusAbsensiList = statusAbsensiBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StatusAbsensiAction.initComboStatusAbsensi] Error when saving error,", e1);
            }
            logger.error("[StatusAbsensiAction.initComboStatusAbsensi] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboStatusAbsensi.addAll(statusAbsensiList);
        logger.info("[StatusAbsensiAction.initComboStatusAbsensi] end process <<<");
        return SUCCESS;
    }

    public StatusAbsensi init(String kode, String flag){
        logger.info("[StatusAbsensiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<StatusAbsensi> listOfResult = (List<StatusAbsensi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (StatusAbsensi statusAbsensi: listOfResult) {
                    if(kode.equalsIgnoreCase(statusAbsensi.getStatusAbsensiId()) && flag.equalsIgnoreCase(statusAbsensi.getFlag())){
                        setStatusAbsensi(statusAbsensi);
                        break;
                    }
                }
            } else {
                setStatusAbsensi(new StatusAbsensi());
            }

            logger.info("[StatusAbsensiAction.init] end process >>>");
        }
        return getStatusAbsensi();
    }

    @Override
    public String add() {
        logger.info("[StatusAbsensiAction.add] start process >>>");
        StatusAbsensi addStatusAbsensi = new StatusAbsensi();
        setStatusAbsensi(addStatusAbsensi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[StatusAbsensiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[StatusAbsensiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        StatusAbsensi editStatusAbsensi = new StatusAbsensi();

        if(itemFlag != null){
            try {
                editStatusAbsensi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiBO.getStatusAbsensiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusAbsensiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[StatusAbsensiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editStatusAbsensi != null) {
                setStatusAbsensi(editStatusAbsensi);
            } else {
                editStatusAbsensi.setFlag(itemFlag);
                editStatusAbsensi.setStatusAbsensiId(itemId);
                setStatusAbsensi(editStatusAbsensi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editStatusAbsensi.setStatusAbsensiId(itemId);
            editStatusAbsensi.setFlag(getFlag());
            setStatusAbsensi(editStatusAbsensi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[StatusAbsensiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[StatusAbsensiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StatusAbsensi deleteStatusAbsensi = new StatusAbsensi();

        if (itemFlag != null ) {

            try {
                deleteStatusAbsensi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusAbsensiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[StatusAbsensiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStatusAbsensi != null) {
                setStatusAbsensi(deleteStatusAbsensi);

            } else {
                deleteStatusAbsensi.setStatusAbsensiId(itemId);
                deleteStatusAbsensi.setFlag(itemFlag);
                setStatusAbsensi(deleteStatusAbsensi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStatusAbsensi.setStatusAbsensiId(itemId);
            deleteStatusAbsensi.setFlag(itemFlag);
            setStatusAbsensi(deleteStatusAbsensi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }


        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[StatusAbsensiAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StatusAbsensi deleteStatusAbsensi = new StatusAbsensi();

        if (itemFlag != null ) {
            try {
                deleteStatusAbsensi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusAbsensiAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[StatusAbsensiAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStatusAbsensi != null) {
                setStatusAbsensi(deleteStatusAbsensi);

            } else {
                deleteStatusAbsensi.setStatusAbsensiId(itemId);
                deleteStatusAbsensi.setFlag(itemFlag);
                setStatusAbsensi(deleteStatusAbsensi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStatusAbsensi.setStatusAbsensiId(itemId);
            deleteStatusAbsensi.setFlag(itemFlag);
            setStatusAbsensi(deleteStatusAbsensi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[StatusAbsensiAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[StatusAbsensiAction.saveEdit] start process >>>");
        try {
            StatusAbsensi editStatusAbsensi = getStatusAbsensi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editStatusAbsensi.setLastUpdateWho(userLogin);
            editStatusAbsensi.setLastUpdate(updateTime);
            editStatusAbsensi.setAction("U");
            editStatusAbsensi.setFlag("Y");

            statusAbsensiBoProxy.saveEdit(editStatusAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StatusAbsensiAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StatusAbsensiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[StatusAbsensiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[StatusAbsensiAction.saveDelete] start process >>>");
        try {
            StatusAbsensi deleteStatusAbsensi = getStatusAbsensi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteStatusAbsensi.setLastUpdate(updateTime);
            deleteStatusAbsensi.setLastUpdateWho(userLogin);
            deleteStatusAbsensi.setAction("U");
            deleteStatusAbsensi.setFlag("N");

            statusAbsensiBoProxy.saveDelete(deleteStatusAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[StatusAbsensiAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StatusAbsensiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[StatusAbsensiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[StatusAbsensiAction.saveAdd] start process >>>");

        try {
            StatusAbsensi statusAbsensi = getStatusAbsensi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            statusAbsensi.setCreatedWho(userLogin);
            statusAbsensi.setLastUpdate(updateTime);
            statusAbsensi.setCreatedDate(updateTime);
            statusAbsensi.setLastUpdateWho(userLogin);
            statusAbsensi.setAction("C");
            statusAbsensi.setFlag("Y");

            statusAbsensiBoProxy.saveAdd(statusAbsensi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[StatusAbsensiAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StatusAbsensiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[StatusAbsensiAction.search] start process >>>");
        StatusAbsensi searchStatusAbsensi = getStatusAbsensi();
        List<StatusAbsensi> listOfsearchStatusAbsensi = new ArrayList();
        try {
            listOfsearchStatusAbsensi = statusAbsensiBoProxy.getByCriteria(searchStatusAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusAbsensiBoProxy.saveErrorMessage(e.getMessage(), "StatusAbsensiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StatusAbsensiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StatusAbsensiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchStatusAbsensi);

        logger.info("[StatusAbsensiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[StatusAbsensiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[StatusAbsensiAction.initForm] end process >>>");
        return INPUT;
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}

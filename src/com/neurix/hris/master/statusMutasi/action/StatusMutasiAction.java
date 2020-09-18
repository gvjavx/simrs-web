package com.neurix.hris.master.statusMutasi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.statusMutasi.bo.StatusMutasiBo;
import com.neurix.hris.master.statusMutasi.model.StatusMutasi;
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

public class StatusMutasiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(StatusMutasiAction.class);
    private StatusMutasiBo statusMutasiBoProxy;
    private StatusMutasi statusMutasi;
    private List<StatusMutasi> listOfComboStatusMutasi = new ArrayList<StatusMutasi>();

    public List<StatusMutasi> getListOfComboStatusMutasi() {
        return listOfComboStatusMutasi;
    }

    public void setListOfComboStatusMutasi(List<StatusMutasi> listOfComboStatusMutasi) {
        this.listOfComboStatusMutasi = listOfComboStatusMutasi;
    }


    public StatusMutasiBo getStatusMutasiBoProxy() {
        return statusMutasiBoProxy;
    }

    public void setStatusMutasiBoProxy(StatusMutasiBo statusMutasiBoProxy) {
        this.statusMutasiBoProxy = statusMutasiBoProxy;
    }

    public StatusMutasi getStatusMutasi() {
        return statusMutasi;
    }

    public void setStatusMutasi(StatusMutasi statusMutasi) {
        this.statusMutasi = statusMutasi;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StatusMutasiAction.logger = logger;
    }


    public String initComboStatusMutasi() {
        logger.info("[StatusMutasiAction.initComboStatusMutasi] start process >>>");

        StatusMutasi search = new StatusMutasi();
        List<StatusMutasi> statusMutasiList = new ArrayList();
        search.setFlag("Y");
        try {
            statusMutasiList = statusMutasiBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StatusMutasiAction.initComboStatusMutasi] Error when saving error,", e1);
            }
            logger.error("[StatusMutasiAction.initComboStatusMutasi] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboStatusMutasi.addAll(statusMutasiList);
        logger.info("[StatusMutasiAction.initComboStatusMutasi] end process <<<");
        return SUCCESS;
    }

    public StatusMutasi init(String kode, String flag){
        logger.info("[StatusMutasiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<StatusMutasi> listOfResult = (List<StatusMutasi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (StatusMutasi statusMutasi: listOfResult) {
                    if(kode.equalsIgnoreCase(statusMutasi.getStatusMutasiId()) && flag.equalsIgnoreCase(statusMutasi.getFlag())){
                        setStatusMutasi(statusMutasi);
                        break;
                    }
                }
            } else {
                setStatusMutasi(new StatusMutasi());
            }

            logger.info("[StatusMutasiAction.init] end process >>>");
        }
        return getStatusMutasi();
    }

    @Override
    public String add() {
        logger.info("[StatusMutasiAction.add] start process >>>");
        StatusMutasi addStatusMutasi = new StatusMutasi();
        setStatusMutasi(addStatusMutasi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[StatusMutasiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[StatusMutasiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        StatusMutasi editStatusMutasi = new StatusMutasi();

        if(itemFlag != null){
            try {
                editStatusMutasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiBO.getStatusMutasiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusMutasiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[StatusMutasiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editStatusMutasi != null) {
                setStatusMutasi(editStatusMutasi);
            } else {
                editStatusMutasi.setFlag(itemFlag);
                editStatusMutasi.setStatusMutasiId(itemId);
                setStatusMutasi(editStatusMutasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editStatusMutasi.setStatusMutasiId(itemId);
            editStatusMutasi.setFlag(getFlag());
            setStatusMutasi(editStatusMutasi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[StatusMutasiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[StatusMutasiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StatusMutasi deleteStatusMutasi = new StatusMutasi();

        if (itemFlag != null ) {

            try {
                deleteStatusMutasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusMutasiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[StatusMutasiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStatusMutasi != null) {
                setStatusMutasi(deleteStatusMutasi);

            } else {
                deleteStatusMutasi.setStatusMutasiId(itemId);
                deleteStatusMutasi.setFlag(itemFlag);
                setStatusMutasi(deleteStatusMutasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStatusMutasi.setStatusMutasiId(itemId);
            deleteStatusMutasi.setFlag(itemFlag);
            setStatusMutasi(deleteStatusMutasi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }


        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[StatusMutasiAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StatusMutasi deleteStatusMutasi = new StatusMutasi();

        if (itemFlag != null ) {
            try {
                deleteStatusMutasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StatusMutasiAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[StatusMutasiAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStatusMutasi != null) {
                setStatusMutasi(deleteStatusMutasi);

            } else {
                deleteStatusMutasi.setStatusMutasiId(itemId);
                deleteStatusMutasi.setFlag(itemFlag);
                setStatusMutasi(deleteStatusMutasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStatusMutasi.setStatusMutasiId(itemId);
            deleteStatusMutasi.setFlag(itemFlag);
            setStatusMutasi(deleteStatusMutasi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[StatusMutasiAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[StatusMutasiAction.saveEdit] start process >>>");
        try {
            StatusMutasi editStatusMutasi = getStatusMutasi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editStatusMutasi.setLastUpdateWho(userLogin);
            editStatusMutasi.setLastUpdate(updateTime);
            editStatusMutasi.setAction("U");
            editStatusMutasi.setFlag("Y");

            statusMutasiBoProxy.saveEdit(editStatusMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StatusMutasiAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StatusMutasiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[StatusMutasiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[StatusMutasiAction.saveDelete] start process >>>");
        try {
            StatusMutasi deleteStatusMutasi = getStatusMutasi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteStatusMutasi.setLastUpdate(updateTime);
            deleteStatusMutasi.setLastUpdateWho(userLogin);
            deleteStatusMutasi.setAction("U");
            deleteStatusMutasi.setFlag("N");

            statusMutasiBoProxy.saveDelete(deleteStatusMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[StatusMutasiAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StatusMutasiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[StatusMutasiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[StatusMutasiAction.saveAdd] start process >>>");

        try {
            StatusMutasi statusMutasi = getStatusMutasi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            statusMutasi.setCreatedWho(userLogin);
            statusMutasi.setLastUpdate(updateTime);
            statusMutasi.setCreatedDate(updateTime);
            statusMutasi.setLastUpdateWho(userLogin);
            statusMutasi.setAction("C");
            statusMutasi.setFlag("Y");

            statusMutasiBoProxy.saveAdd(statusMutasi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[StatusMutasiAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StatusMutasiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
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
        logger.info("[StatusMutasiAction.search] start process >>>");
        StatusMutasi searchStatusMutasi = getStatusMutasi();
        List<StatusMutasi> listOfsearchStatusMutasi = new ArrayList();
        try {
            listOfsearchStatusMutasi = statusMutasiBoProxy.getByCriteria(searchStatusMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusMutasiBoProxy.saveErrorMessage(e.getMessage(), "StatusMutasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StatusMutasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StatusMutasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchStatusMutasi);

        logger.info("[StatusMutasiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[StatusMutasiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[StatusMutasiAction.initForm] end process >>>");
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

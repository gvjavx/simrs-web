package com.neurix.hris.master.pelatihanJabatan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.pelatihanJabatan.bo.PelatihanJabatanBo;
import com.neurix.hris.master.pelatihanJabatan.model.PelatihanJabatan;
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

public class PelatihanJabatanAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PelatihanJabatanAction.class);
    private PelatihanJabatanBo pelatihanJabatanBoProxy;
    private PelatihanJabatan pelatihanJabatan;

    private List<PelatihanJabatan> comboListOfPelatihanJabatan = new ArrayList<PelatihanJabatan>();

    public List<PelatihanJabatan> getComboListOfPelatihanJabatan() {
        return comboListOfPelatihanJabatan;
    }

    public void setComboListOfPelatihanJabatan(List<PelatihanJabatan> comboListOfPelatihanJabatan) {
        this.comboListOfPelatihanJabatan = comboListOfPelatihanJabatan;
    }

    public PelatihanJabatanBo getPelatihanJabatanBoProxy() {
        return pelatihanJabatanBoProxy;
    }

    public void setPelatihanJabatanBoProxy(PelatihanJabatanBo pelatihanJabatanBoProxy) {
        this.pelatihanJabatanBoProxy = pelatihanJabatanBoProxy;
    }

    public PelatihanJabatan getPelatihanJabatan() {
        return pelatihanJabatan;
    }

    public void setPelatihanJabatan(PelatihanJabatan pelatihanJabatan) {
        this.pelatihanJabatan = pelatihanJabatan;
    }

    private List<PelatihanJabatan> initComboPelatihanJabatan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PelatihanJabatanAction.logger = logger;
    }


    public List<PelatihanJabatan> getInitComboPelatihanJabatan() {
        return initComboPelatihanJabatan;
    }

    public void setInitComboPelatihanJabatan(List<PelatihanJabatan> initComboPelatihanJabatan) {
        this.initComboPelatihanJabatan = initComboPelatihanJabatan;
    }

    public PelatihanJabatan init(String kode, String flag){
        logger.info("[PelatihanJabatanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PelatihanJabatan> listOfResult = (List<PelatihanJabatan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PelatihanJabatan pelatihanJabatan: listOfResult) {
                    if(kode.equalsIgnoreCase(pelatihanJabatan.getPelatihanId()) && flag.equalsIgnoreCase(pelatihanJabatan.getFlag())){
                        setPelatihanJabatan(pelatihanJabatan);
                        break;
                    }
                }
            } else {
                setPelatihanJabatan(new PelatihanJabatan());
            }

            logger.info("[PelatihanJabatanAction.init] end process >>>");
        }
        return getPelatihanJabatan();
    }

    @Override
    public String add() {
        logger.info("[PelatihanJabatanAction.add] start process >>>");
        PelatihanJabatan addPelatihanJabatan = new PelatihanJabatan();
        setPelatihanJabatan(addPelatihanJabatan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PelatihanJabatanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PelatihanJabatanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PelatihanJabatan editPelatihanJabatan = new PelatihanJabatan();

        if(itemFlag != null){
            try {
                editPelatihanJabatan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.getPelatihanJabatanByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PelatihanJabatanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PelatihanJabatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPelatihanJabatan != null) {
                setPelatihanJabatan(editPelatihanJabatan);
            } else {
                editPelatihanJabatan.setFlag(itemFlag);
                editPelatihanJabatan.setPelatihanId(itemId);
                setPelatihanJabatan(editPelatihanJabatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPelatihanJabatan.setPelatihanId(itemId);
            editPelatihanJabatan.setFlag(getFlag());
            setPelatihanJabatan(editPelatihanJabatan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PelatihanJabatanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PelatihanJabatanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PelatihanJabatan deletePelatihanJabatan = new PelatihanJabatan();

        if (itemFlag != null ) {

            try {
                deletePelatihanJabatan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PelatihanJabatanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PelatihanJabatanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePelatihanJabatan != null) {
                setPelatihanJabatan(deletePelatihanJabatan);

            } else {
                deletePelatihanJabatan.setPelatihanId(itemId);
                deletePelatihanJabatan.setFlag(itemFlag);
                setPelatihanJabatan(deletePelatihanJabatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePelatihanJabatan.setPelatihanId(itemId);
            deletePelatihanJabatan.setFlag(itemFlag);
            setPelatihanJabatan(deletePelatihanJabatan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PelatihanJabatanAction.delete] end process <<<");

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
        logger.info("[PelatihanJabatanAction.saveEdit] start process >>>");
        try {

            PelatihanJabatan editPelatihanJabatan = getPelatihanJabatan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPelatihanJabatan.setLastUpdateWho(userLogin);
            editPelatihanJabatan.setLastUpdate(updateTime);
            editPelatihanJabatan.setAction("U");
            editPelatihanJabatan.setFlag("Y");

            pelatihanJabatanBoProxy.saveEdit(editPelatihanJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PelatihanJabatanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelatihanJabatanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PelatihanJabatanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PelatihanJabatanAction.saveDelete] start process >>>");
        try {

            PelatihanJabatan deletePelatihanJabatan = getPelatihanJabatan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePelatihanJabatan.setLastUpdate(updateTime);
            deletePelatihanJabatan.setLastUpdateWho(userLogin);
            deletePelatihanJabatan.setAction("U");
            deletePelatihanJabatan.setFlag("N");

            pelatihanJabatanBoProxy.saveDelete(deletePelatihanJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PelatihanJabatanAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelatihanJabatanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PelatihanJabatanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PelatihanJabatanAction.saveAdd] start process >>>");

        try {
            PelatihanJabatan pelatihanJabatan = getPelatihanJabatan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            pelatihanJabatan.setCreatedWho(userLogin);
            pelatihanJabatan.setLastUpdate(updateTime);
            pelatihanJabatan.setCreatedDate(updateTime);
            pelatihanJabatan.setLastUpdateWho(userLogin);
            pelatihanJabatan.setAction("C");
            pelatihanJabatan.setFlag("Y");

            pelatihanJabatanBoProxy.saveAdd(pelatihanJabatan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PelatihanJabatanAction.search] start process >>>");

        PelatihanJabatan searchPelatihanJabatan = getPelatihanJabatan();
        List<PelatihanJabatan> listOfsearchPelatihanJabatan = new ArrayList();

        try {
            listOfsearchPelatihanJabatan = pelatihanJabatanBoProxy.getByCriteria(searchPelatihanJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PelatihanJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelatihanJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPelatihanJabatan);

        logger.info("[PelatihanJabatanAction.search] end process <<<");

        return SUCCESS;
    }



    public String searchKelompok() {
        logger.info("[PelatihanJabatanAction.search] start process >>>");

        PelatihanJabatan searchPelatihanJabatan = new PelatihanJabatan();

        searchPelatihanJabatan.setFlag("Y");
        List<PelatihanJabatan> listOfsearchPelatihanJabatan = new ArrayList();

        try {
            listOfsearchPelatihanJabatan = pelatihanJabatanBoProxy.getByCriteria(searchPelatihanJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PelatihanJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelatihanJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        comboListOfPelatihanJabatan.addAll(listOfsearchPelatihanJabatan);

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PelatihanJabatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PelatihanJabatanAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPelatihanJabatan() {
        logger.info("[PelatihanJabatanAction.search] start process >>>");

        PelatihanJabatan searchPelatihanJabatan = new PelatihanJabatan();
        searchPelatihanJabatan.setFlag("Y");
        List<PelatihanJabatan> listOfsearchPelatihanJabatan = new ArrayList();

        try {
            listOfsearchPelatihanJabatan = pelatihanJabatanBoProxy.getByCriteria(searchPelatihanJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelatihanJabatanBoProxy.saveErrorMessage(e.getMessage(), "PelatihanJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PelatihanJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelatihanJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPelatihanJabatan");
        session.setAttribute("listOfResultPelatihanJabatan", listOfsearchPelatihanJabatan);

        logger.info("[PelatihanJabatanAction.search] end process <<<");

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

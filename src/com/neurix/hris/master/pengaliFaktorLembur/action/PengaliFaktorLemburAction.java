package com.neurix.hris.master.pengaliFaktorLembur.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.pengaliFaktorLembur.bo.PengaliFaktorLemburBo;
import com.neurix.hris.transaksi.lembur.model.PengaliFaktorLembur;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PengaliFaktorLemburAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PengaliFaktorLemburAction.class);
    private PengaliFaktorLemburBo pengaliFaktorLemburBoProxy;
    private PengaliFaktorLembur pengaliFaktorLembur;


    private List<PengaliFaktorLembur> initComboAlat;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PengaliFaktorLemburAction.logger = logger;
    }

    public PengaliFaktorLemburBo getPengaliFaktorLemburBoProxy() {
        return pengaliFaktorLemburBoProxy;
    }

    public void setPengaliFaktorLemburBoProxy(PengaliFaktorLemburBo pengaliFaktorLemburBoProxy) {
        this.pengaliFaktorLemburBoProxy = pengaliFaktorLemburBoProxy;
    }

    public PengaliFaktorLembur getPengaliFaktorLembur() {
        return pengaliFaktorLembur;
    }

    public void setPengaliFaktorLembur(PengaliFaktorLembur pengaliFaktorLembur) {
        this.pengaliFaktorLembur = pengaliFaktorLembur;
    }

    public List<PengaliFaktorLembur> getInitComboAlat() {
        return initComboAlat;
    }

    public void setInitComboAlat(List<PengaliFaktorLembur> initComboAlat) {
        this.initComboAlat = initComboAlat;
    }

    public PengaliFaktorLembur init(String kode, String flag){
        logger.info("[AlatAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengaliFaktorLembur> listOfResult = (List<PengaliFaktorLembur>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PengaliFaktorLembur pengaliFaktorLembur: listOfResult) {
                    if(kode.equalsIgnoreCase(pengaliFaktorLembur.getPengaliFaktorId()) && flag.equalsIgnoreCase(pengaliFaktorLembur.getFlag())){
                        setPengaliFaktorLembur(pengaliFaktorLembur);
                        break;
                    }
                }
            } else {
                setPengaliFaktorLembur(new PengaliFaktorLembur());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getPengaliFaktorLembur();
    }

    @Override
    public String add() {
        logger.info("[AlatAction.add] start process >>>");
        PengaliFaktorLembur addAlat = new PengaliFaktorLembur();
        setPengaliFaktorLembur(addAlat);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[AlatAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[AlatAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PengaliFaktorLembur editPengaliFaktorLembur = new PengaliFaktorLembur();

        if(itemFlag != null){
            try {
                editPengaliFaktorLembur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pengaliFaktorLemburBoProxy.saveErrorMessage(e.getMessage(), "PengaliFaktorLemburBO.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PengaliFaktorLemburAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AlatAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPengaliFaktorLembur != null) {
                setPengaliFaktorLembur(editPengaliFaktorLembur);
            } else {
                editPengaliFaktorLembur.setFlag(itemFlag);
                editPengaliFaktorLembur.setPengaliFaktorId(itemId);
                setPengaliFaktorLembur(editPengaliFaktorLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPengaliFaktorLembur.setPengaliFaktorId(itemId);
            editPengaliFaktorLembur.setFlag(getFlag());
            setPengaliFaktorLembur(editPengaliFaktorLembur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PengaliFaktorLemburAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PengaliFaktorLembur deletePengaliFaktorLembur = new PengaliFaktorLembur();

        if (itemFlag != null ) {

            try {
                deletePengaliFaktorLembur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pengaliFaktorLemburBoProxy.saveErrorMessage(e.getMessage(), "PengaliFaktorLemburBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PengaliFaktorLemburAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePengaliFaktorLembur != null) {
                setPengaliFaktorLembur(deletePengaliFaktorLembur);

            } else {
                deletePengaliFaktorLembur.setPengaliFaktorId(itemId);
                deletePengaliFaktorLembur.setFlag(itemFlag);
                setPengaliFaktorLembur(deletePengaliFaktorLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePengaliFaktorLembur.setPengaliFaktorId(itemId);
            deletePengaliFaktorLembur.setFlag(itemFlag);
            setPengaliFaktorLembur(deletePengaliFaktorLembur);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AlatAction.delete] end process <<<");

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
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {

            PengaliFaktorLembur editPengaliFaktorLembur = getPengaliFaktorLembur();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            editPengaliFaktorLembur.setLastUpdateWho(userLogin);
            editPengaliFaktorLembur.setLastUpdate(updateTime);
            editPengaliFaktorLembur.setAction("U");
            editPengaliFaktorLembur.setFlag("Y");

            pengaliFaktorLemburBoProxy.saveEdit(editPengaliFaktorLembur);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengaliFaktorLemburBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PengaliFaktorLemburAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengaliFaktorLemburAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengaliFaktorLemburAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PengaliFaktorLemburAction.saveDelete] start process >>>");
        try {

            PengaliFaktorLembur deletePengaliFaktorLembur = getPengaliFaktorLembur();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePengaliFaktorLembur.setLastUpdate(updateTime);
            deletePengaliFaktorLembur.setLastUpdateWho(userLogin);
            deletePengaliFaktorLembur.setAction("U");
            deletePengaliFaktorLembur.setFlag("N");

            pengaliFaktorLemburBoProxy.saveDelete(deletePengaliFaktorLembur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengaliFaktorLemburBoProxy.saveErrorMessage(e.getMessage(), "PengaliFaktorLemburBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AlatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        try {
            PengaliFaktorLembur pengaliFaktorLembur = getPengaliFaktorLembur();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pengaliFaktorLembur.setCreatedWho(userLogin);
            pengaliFaktorLembur.setLastUpdate(updateTime);
            pengaliFaktorLembur.setCreatedDate(updateTime);
            pengaliFaktorLembur.setLastUpdateWho(userLogin);
            pengaliFaktorLembur.setAction("C");
            pengaliFaktorLembur.setFlag("Y");
            pengaliFaktorLemburBoProxy.saveAdd(pengaliFaktorLembur);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengaliFaktorLemburBoProxy.saveErrorMessage(e.getMessage(), "pengaliFaktorLemburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pengaliFaktorLemburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[pengaliFaktorLemburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[pengaliFaktorLemburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[AlatAction.search] start process >>>");

        PengaliFaktorLembur searchAlat = getPengaliFaktorLembur();
        List<PengaliFaktorLembur> listOfSearchPengaliFaktorLembur = new ArrayList();

        try {
            listOfSearchPengaliFaktorLembur = pengaliFaktorLemburBoProxy.getByCriteria(searchAlat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengaliFaktorLemburBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchPengaliFaktorLembur);

        logger.info("[AlatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AlatAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[AlatAction.initForm] end process >>>");
        return INPUT;
    }

    public List initComboAlat(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<PengaliFaktorLembur> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengaliFaktorLemburBo pengaliFaktorLemburBo = (PengaliFaktorLemburBo) ctx.getBean("alatBoProxy");

        try {
            listOfAlat = pengaliFaktorLemburBo.getComboPengaliFaktorLemburWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengaliFaktorLemburBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
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

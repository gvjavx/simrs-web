package com.neurix.hris.master.jamLembur.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.jamLembur.bo.JamLemburBo;
import com.neurix.hris.transaksi.lembur.model.JamLembur;
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

public class JamLemburAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(JamLemburAction.class);
    private JamLemburBo jamLemburBoProxy;
    private JamLembur jamLembur;


    private List<JamLembur> initComboAlat;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JamLemburAction.logger = logger;
    }

    public JamLemburBo getJamLemburBoProxy() {
        return jamLemburBoProxy;
    }

    public void setJamLemburBoProxy(JamLemburBo jamLemburBoProxy) {
        this.jamLemburBoProxy = jamLemburBoProxy;
    }

    public JamLembur getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(JamLembur jamLembur) {
        this.jamLembur = jamLembur;
    }

    public List<JamLembur> getInitComboAlat() {
        return initComboAlat;
    }

    public void setInitComboAlat(List<JamLembur> initComboAlat) {
        this.initComboAlat = initComboAlat;
    }

    public JamLembur init(String kode, String flag){
        logger.info("[AlatAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JamLembur> listOfResult = (List<JamLembur>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (JamLembur jamLembur: listOfResult) {
                    if(kode.equalsIgnoreCase(jamLembur.getJamLemburId()) && flag.equalsIgnoreCase(jamLembur.getFlag())){
                        setJamLembur(jamLembur);
                        break;
                    }
                }
            } else {
                setJamLembur(new JamLembur());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getJamLembur();
    }

    @Override
    public String add() {
        logger.info("[AlatAction.add] start process >>>");
        JamLembur addAlat = new JamLembur();
        setJamLembur(addAlat);
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

        JamLembur editJamLembur = new JamLembur();

        if(itemFlag != null){
            try {
                editJamLembur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jamLemburBoProxy.saveErrorMessage(e.getMessage(), "JamLemburBO.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[JamLemburAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AlatAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editJamLembur != null) {
                setJamLembur(editJamLembur);
            } else {
                editJamLembur.setFlag(itemFlag);
                editJamLembur.setJamLemburId(itemId);
                setJamLembur(editJamLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJamLembur.setJamLemburId(itemId);
            editJamLembur.setFlag(getFlag());
            setJamLembur(editJamLembur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[JamLemburAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JamLembur deleteJamLembur = new JamLembur();

        if (itemFlag != null ) {

            try {
                deleteJamLembur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jamLemburBoProxy.saveErrorMessage(e.getMessage(), "JamLemburBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[JamLemburAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteJamLembur != null) {
                setJamLembur(deleteJamLembur);

            } else {
                deleteJamLembur.setJamLemburId(itemId);
                deleteJamLembur.setFlag(itemFlag);
                setJamLembur(deleteJamLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteJamLembur.setJamLemburId(itemId);
            deleteJamLembur.setFlag(itemFlag);
            setJamLembur(deleteJamLembur);
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

            JamLembur editJamLembur = getJamLembur();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            editJamLembur.setLastUpdateWho(userLogin);
            editJamLembur.setLastUpdate(updateTime);
            editJamLembur.setAction("U");
            editJamLembur.setFlag("Y");

            jamLemburBoProxy.saveEdit(editJamLembur);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamLemburBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[JamLemburAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JamLemburAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[JamLemburAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[JamLemburAction.saveDelete] start process >>>");
        try {

            JamLembur deleteJamLembur = getJamLembur();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJamLembur.setLastUpdate(updateTime);
            deleteJamLembur.setLastUpdateWho(userLogin);
            deleteJamLembur.setAction("U");
            deleteJamLembur.setFlag("N");

            jamLemburBoProxy.saveDelete(deleteJamLembur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamLemburBoProxy.saveErrorMessage(e.getMessage(), "JamLemburBO.saveDelete");
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
            JamLembur jamLembur = getJamLembur();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jamLembur.setCreatedWho(userLogin);
            jamLembur.setLastUpdate(updateTime);
            jamLembur.setCreatedDate(updateTime);
            jamLembur.setLastUpdateWho(userLogin);
            jamLembur.setAction("C");
            jamLembur.setFlag("Y");
            jamLemburBoProxy.saveAdd(jamLembur);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamLemburBoProxy.saveErrorMessage(e.getMessage(), "jamLemburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[jamLemburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[jamLemburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[jamLemburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[AlatAction.search] start process >>>");

        JamLembur searchAlat = getJamLembur();
        List<JamLembur> listOfSearchJamLembur = new ArrayList();

        try {
            listOfSearchJamLembur = jamLemburBoProxy.getByCriteria(searchAlat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamLemburBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.getByCriteria");
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
        session.setAttribute("listOfResult", listOfSearchJamLembur);

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

        List<JamLembur> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JamLemburBo jamLemburBo = (JamLemburBo) ctx.getBean("alatBoProxy");

        try {
            listOfAlat = jamLemburBo.getComboJamLemburWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamLemburBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
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

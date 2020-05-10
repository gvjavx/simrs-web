package com.neurix.simrs.master.asuransi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AsuransiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(AsuransiAction.class);
    Asuransi asuransi;
    AsuransiBo asuransiBoProxy;

    public Asuransi getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(Asuransi asuransi) {
        this.asuransi = asuransi;
    }

    public AsuransiBo getAsuransiBoProxy() {
        return asuransiBoProxy;
    }

    public void setAsuransiBoProxy(AsuransiBo asuransiBoProxy) {
        this.asuransiBoProxy = asuransiBoProxy;
    }

    public Asuransi init(String kode, String flag){
        logger.info("[PayrollSkalaGajiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Asuransi> listOfResult = (List<Asuransi>) session.getAttribute("listOfResultAsuransi");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Asuransi asuransi: listOfResult) {
                    if(kode.equalsIgnoreCase(asuransi.getIdAsuransi()) && flag.equalsIgnoreCase(asuransi.getFlag())){
                        setAsuransi(asuransi);
                        break;
                    }
                }
            } else {
                setAsuransi(new Asuransi());
            }

            logger.info("[PayrollSkalaGajiAction.init] end process >>>");
        }
        return getAsuransi();
    }

    @Override
    public String add() {
        logger.info("[PelayananAction.add] start process >>>");
        Asuransi addAsuransi = new Asuransi();
        setAsuransi(addAsuransi);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Asuransi editAsuransi = new Asuransi();

        if(itemFlag != null){
            try {
                editAsuransi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = asuransiBoProxy.saveErrorMessage(e.getMessage(), "AsuransiBoImpl.getPayrollSkalaGajiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[AsuransiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AsuransiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editAsuransi != null) {
                setAsuransi(editAsuransi);
            } else {
                editAsuransi.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setAsuransi(editAsuransi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editAsuransi.setFlag(getFlag());
            setAsuransi(editAsuransi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[AsuransiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PelayananAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Asuransi deleteAsuransi = new Asuransi();

        if (itemFlag != null ) {
            try {
                deleteAsuransi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = asuransiBoProxy.saveErrorMessage(e.getMessage(), "AsuransiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AsuransiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[AsuransiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteAsuransi != null) {
                setAsuransi(deleteAsuransi);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteAsuransi.setFlag(itemFlag);
                setAsuransi(deleteAsuransi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteAsuransi.setFlag(itemFlag);
            setAsuransi(deleteAsuransi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AsuransiAction.delete] end process <<<");

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

    public String saveAdd(){
        logger.info("[PelayananAction.saveAdd] start process >>>");

        try {
            Asuransi asuransi = getAsuransi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            asuransi.setCreatedWho(userLogin);
            asuransi.setLastUpdate(updateTime);
            asuransi.setCreatedDate(updateTime);
            asuransi.setLastUpdateWho(userLogin);
            asuransi.setAction("C");
            asuransi.setFlag("Y");

            asuransiBoProxy.saveAdd(asuransi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = asuransiBoProxy.saveErrorMessage(e.getMessage(), "payrollSkalaGajiBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pelayananAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[pelayananAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultAsuransi");

        logger.info("[AsuransiAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[AsuransiAction.saveEdit] start process >>>");
        try {

            Asuransi editAsuransi = getAsuransi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editAsuransi.setLastUpdateWho(userLogin);
            editAsuransi.setLastUpdate(updateTime);
            editAsuransi.setAction("U");
            editAsuransi.setFlag("Y");

            asuransiBoProxy.saveEdit(editAsuransi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = asuransiBoProxy.saveErrorMessage(e.getMessage(), "AsuransiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[AsuransiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AsuransiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AsuransiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiAction.saveDelete] start process >>>");
        try {

            Asuransi deleteAsuransi = getAsuransi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteAsuransi.setLastUpdate(updateTime);
            deleteAsuransi.setLastUpdateWho(userLogin);
            deleteAsuransi.setAction("U");
            deleteAsuransi.setFlag("N");

            asuransiBoProxy.saveDelete(deleteAsuransi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = asuransiBoProxy.saveErrorMessage(e.getMessage(), "AsuransiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AsuransiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AsuransiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AsuransiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String search() {
        logger.info("[AsuransuAction.search] start process >>>");

        Asuransi searchAsuransi = getAsuransi();
        List<Asuransi> listOfsearchAsuransi = new ArrayList();

        try {
            listOfsearchAsuransi = asuransiBoProxy.getByCriteria(searchAsuransi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = asuransiBoProxy.saveErrorMessage(e.getMessage(), "AsuransiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AsuransiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AsuransiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultAsuransi");
        session.setAttribute("listOfResultAsuransi", listOfsearchAsuransi);

        logger.info("[AsuransiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AsuransiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultAsuransi");
        logger.info("[AsuransiAction.initForm] end process >>>");
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
}
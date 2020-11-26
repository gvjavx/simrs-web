package com.neurix.hris.transaksi.absensi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiBo;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MesinAbsensiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(MesinAbsensiAction.class);
    MesinAbsensi mesinAbsensi;
    MesinAbsensiBo mesinAbsensiBoProxy;

    public MesinAbsensi getMesinAbsensi() {
        return mesinAbsensi;
    }

    public void setMesinAbsensi(MesinAbsensi mesinAbsensi) {
        this.mesinAbsensi = mesinAbsensi;
    }

    public MesinAbsensiBo getMesinAbsensiBoProxy() {
        return mesinAbsensiBoProxy;
    }

    public void setMesinAbsensiBoProxy(MesinAbsensiBo mesinAbsensiBoProxy) {
        this.mesinAbsensiBoProxy = mesinAbsensiBoProxy;
    }

    public MesinAbsensi init(String kode, String flag){
        logger.info("[MesinAbsensiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MesinAbsensi> listOfResult = (List<MesinAbsensi>) session.getAttribute("listOfResultMesinAbsensi");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MesinAbsensi mesinAbsensi: listOfResult) {
                    if (kode.equalsIgnoreCase(mesinAbsensi.getMesinId()) && flag.equalsIgnoreCase(mesinAbsensi.getFlag())){
                        setMesinAbsensi(mesinAbsensi);
                        break;
                    }
                }
            } else {
                setMesinAbsensi(new MesinAbsensi());
            }

            logger.info("[MesinAbsensiAction.init] end process >>>");
        }
        return getMesinAbsensi();
    }

    @Override
    public String add() {
        logger.info("[MesinAbsensiAction.add] start process >>>");
        MesinAbsensi addMesinAbsensi = new MesinAbsensi();
        setMesinAbsensi(addMesinAbsensi);
        setAddOrEdit(true);
        setAdd(true);


        logger.info("[MesinAbsensiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MesinAbsensiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MesinAbsensi editMesinAbsensi = new MesinAbsensi();

        if(itemFlag != null){
            try {
                editMesinAbsensi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mesinAbsensiBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensi.init");
                } catch (GeneralBOException e1) {
                    logger.error("[MesinAbsensiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MesinAbsensiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMesinAbsensi != null) {
                setMesinAbsensi(editMesinAbsensi);
            } else {
                editMesinAbsensi.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setMesinAbsensi(editMesinAbsensi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editMesinAbsensi.setFlag(getFlag());
            setMesinAbsensi(editMesinAbsensi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[MesinAbsensiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MesinAbsensiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MesinAbsensi deleteMesinAbsensi = new MesinAbsensi();

        if (itemFlag != null ) {

            try {
                deleteMesinAbsensi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mesinAbsensiBoProxy.saveErrorMessage(e.getMessage(), "MesinBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMesinAbsensi != null) {
                setMesinAbsensi(deleteMesinAbsensi);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteMesinAbsensi.setFlag(itemFlag);
                setMesinAbsensi(deleteMesinAbsensi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteMesinAbsensi.setFlag(itemFlag);
            setMesinAbsensi(deleteMesinAbsensi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiAction.delete] end process <<<");

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
        logger.info("[MesinAbsensiAction.search] start process >>>");

        MesinAbsensi searchMesinAbsensi = getMesinAbsensi();
        List<MesinAbsensi> listOfsearchMesinAbsensi = new ArrayList();

        try {
            listOfsearchMesinAbsensi = mesinAbsensiBoProxy.getByCriteria(searchMesinAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MesinAbsensiAction.save] Error when searching mesin absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMesinAbsensi");
        session.setAttribute("listOfResultMesinAbsensi", listOfsearchMesinAbsensi);

        logger.info("[MesinAbsensiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MesinAbsensiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMesinAbsensi");
        logger.info("[MesinAbsensiAction.initForm] end process >>>");
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

    public String saveAdd(){
        logger.info("[MesinAbsensiAction.saveAdd] start process >>>");

        try {
            MesinAbsensi mesinAbsensi = getMesinAbsensi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            mesinAbsensi.setCreatedWho(userLogin);
            mesinAbsensi.setLastUpdate(updateTime);
            mesinAbsensi.setCreatedDate(updateTime);
            mesinAbsensi.setLastUpdateWho(userLogin);
            mesinAbsensi.setAction("C");
            mesinAbsensi.setFlag("Y");

            mesinAbsensiBoProxy.saveAdd(mesinAbsensi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[MesinAbsensiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMesinAbsensi");

        logger.info("[MesinAbsensiAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[MesinAbsensiAction.saveEdit] start process >>>");
        try {

            MesinAbsensi editMesinAbsensi = getMesinAbsensi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMesinAbsensi.setLastUpdateWho(userLogin);
            editMesinAbsensi.setLastUpdate(updateTime);
            editMesinAbsensi.setAction("U");
            editMesinAbsensi.setFlag("Y");

            mesinAbsensiBoProxy.saveEdit(editMesinAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[MesinAbsensiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[MesinAbsensiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MesinAbsensiAction.saveDelete] start process >>>");
        try {

            MesinAbsensi deleteMesinAbsensi = getMesinAbsensi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteMesinAbsensi.setLastUpdate(updateTime);
            deleteMesinAbsensi.setLastUpdateWho(userLogin);
            deleteMesinAbsensi.setAction("U");
            deleteMesinAbsensi.setFlag("N");

            mesinAbsensiBoProxy.saveDelete(deleteMesinAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[MesinAbsensiAction.saveDelete] Error when editing item mesinAbsensi," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[MesinAbsensiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
}
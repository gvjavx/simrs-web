package com.neurix.hris.master.medicalBiayaKacamata.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.medicalBiayaKacamata.bo.MedicalBiayaKacamataBo;
import com.neurix.hris.master.medicalBiayaKacamata.model.MedicalBiayaKacamata;
import com.neurix.hris.transaksi.medicalKacamata.model.MedicalKacamata;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class MedicalBiayaKacamataAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(MedicalBiayaKacamataAction.class);
    private MedicalBiayaKacamataBo medicalBiayaKacamataBoProxy;
    private MedicalBiayaKacamata medicalBiayaKacamata;

    private List<MedicalBiayaKacamata> listComboGolongan = new ArrayList<MedicalBiayaKacamata>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MedicalBiayaKacamataAction.logger = logger;
    }

    public MedicalBiayaKacamataBo getMedicalBiayaKacamataBoProxy() {
        return medicalBiayaKacamataBoProxy;
    }

    public void setMedicalBiayaKacamataBoProxy(MedicalBiayaKacamataBo medicalBiayaKacamataBoProxy) {
        this.medicalBiayaKacamataBoProxy = medicalBiayaKacamataBoProxy;
    }

    public MedicalBiayaKacamata getMedicalBiayaKacamata() {
        return medicalBiayaKacamata;
    }

    public void setMedicalBiayaKacamata(MedicalBiayaKacamata medicalBiayaKacamata) {
        this.medicalBiayaKacamata = medicalBiayaKacamata;
    }

    public List<MedicalBiayaKacamata> getListComboGolongan() {
        return listComboGolongan;
    }

    public void setListComboGolongan(List<MedicalBiayaKacamata> listComboGolongan) {
        this.listComboGolongan = listComboGolongan;
    }

    public String initComboGolongan() {
        logger.info("[BranchAction.search] start process >>>");

        MedicalBiayaKacamata searchGolongan = new MedicalBiayaKacamata();
        List<MedicalBiayaKacamata> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = medicalBiayaKacamataBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboGolongan.addAll(listOfSearchGolongan);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public MedicalBiayaKacamata init(String kode, String flag){
        logger.info("[UpdateMedicalBiayaKacamata.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalBiayaKacamata> listOfResult = (List<MedicalBiayaKacamata>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MedicalBiayaKacamata biayaLoop: listOfResult) {
                    if(kode.equalsIgnoreCase(biayaLoop.getBiayaKacamataId()) && flag.equalsIgnoreCase(biayaLoop.getFlag())){
                        setMedicalBiayaKacamata(biayaLoop);
                        break;
                    }
                }
            } else {
                setMedicalBiayaKacamata(new MedicalBiayaKacamata());
            }

            logger.info("[UpdateMedicalBiayaKacamata.init] end process >>>");
        }
        return getMedicalBiayaKacamata();
    }

    @Override
    public String add() {
        logger.info("[UpdateMedicalBiayaKacamata.add] start process >>>");
        MedicalBiayaKacamata addGolongan = new MedicalBiayaKacamata();
        setMedicalBiayaKacamata(addGolongan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[UpdateMedicalBiayaKacamata.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[UpdateMedicalBiayaKacamata.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MedicalBiayaKacamata biayaKacamata = new MedicalBiayaKacamata();

        if(itemFlag != null){
            try {
                biayaKacamata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "MedicalBiayaKacamata.getGolonganByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalBiayaKacamata.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MedicalBiayaKacamata.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(biayaKacamata != null) {
                setMedicalBiayaKacamata(biayaKacamata);
            } else {
                biayaKacamata.setFlag(itemFlag);
                biayaKacamata.setGolonganId(itemId);
                setMedicalBiayaKacamata(biayaKacamata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            biayaKacamata.setGolonganId(itemId);
            biayaKacamata.setFlag(getFlag());
            setMedicalBiayaKacamata(biayaKacamata);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);

        logger.info("[UpdateMedicalBiayaKacamata.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MedicalBiayaKacamata.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MedicalBiayaKacamata biayaKacamata = new MedicalBiayaKacamata();

        if (itemFlag != null ) {

            try {
                biayaKacamata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "MedicalBiayaKacamata.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalBiayaKacamata.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MedicalBiayaKacamata.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (biayaKacamata != null) {
                setMedicalBiayaKacamata(biayaKacamata);

            } else {
                biayaKacamata.setFlag(itemFlag);
                setMedicalBiayaKacamata(biayaKacamata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            biayaKacamata.setFlag(itemFlag);
            setMedicalBiayaKacamata(biayaKacamata);
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
        logger.info("[MedicalBiayaKacamata.saveEdit] start process >>>");
        try {

            MedicalBiayaKacamata editGolongan = getMedicalBiayaKacamata();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            medicalBiayaKacamataBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "MedicalBiayaKacamata.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalBiayaKacamata.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MedicalBiayaKacamata.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MedicalBiayaKacamata.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MedicalBiayaKacamata.saveDelete] start process >>>");
        try {

            MedicalBiayaKacamata deleteGolongan = getMedicalBiayaKacamata();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            medicalBiayaKacamataBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.saveDelete");
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
            MedicalBiayaKacamata BiayaKacamata = getMedicalBiayaKacamata();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            BiayaKacamata.setCreatedWho(userLogin);
            BiayaKacamata.setLastUpdate(updateTime);
            BiayaKacamata.setCreatedDate(updateTime);
            BiayaKacamata.setLastUpdateWho(userLogin);
            BiayaKacamata.setAction("C");
            BiayaKacamata.setFlag("Y");

            medicalBiayaKacamataBoProxy.saveAdd(BiayaKacamata);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "medicalBiayaKacamataBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[medicalBiayaKacamata.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[medicalBiayaKacamata.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[medicalBiayaKacamata.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[MedicalBiayaKacamata.search] start process >>>");

        MedicalBiayaKacamata searchGolongan = getMedicalBiayaKacamata();
        List<MedicalBiayaKacamata> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = medicalBiayaKacamataBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "MedicalBiayaKacamata.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalBiayaKacamata.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MedicalBiayaKacamata.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchGolongan);

        logger.info("[AlatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MedicalBiayaKacamata.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listDataGolongan");
        logger.info("[MedicalBiayaKacamata.initForm] end process >>>");
        return INPUT;
    }

    public String paging(){
        return SUCCESS;
    }

    public String printGolongan(){
        String id = getId();
        int periode = Integer.parseInt(getPeriode()) - 1;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalBiayaKacamataBo updateGolonganBo = (MedicalBiayaKacamataBo) ctx.getBean("medicalBiayaKacamataBoProxy");


        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Report Kenaikan Golongan");

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId =medicalBiayaKacamataBoProxy.saveErrorMessage(e.getMessage(), "searchReport");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.searchReport] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.searchReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }

        return "print_report_golongan";
    }

    public String getBiaya(String branchId, String golonganId, String tipe){
        logger.info("[MedicalBiayaKacamata.getBiaya] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalBiayaKacamataBo medicalBiayaKacamataBo = (MedicalBiayaKacamataBo) ctx.getBean("medicalBiayaKacamataBoProxy");

        String hasil = "0" ;
        hasil = medicalBiayaKacamataBo.getBiaya(branchId, golonganId, tipe);

        logger.info("[MedicalBiayaKacamata.getBiaya] end process >>>");
        return hasil;
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

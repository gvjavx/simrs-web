package com.neurix.hris.transaksi.medicalKacamata.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.medicalKacamata.bo.MedicalKacamataBo;
import com.neurix.hris.transaksi.medicalKacamata.model.MedicalKacamata;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class MedicalKacamataAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(MedicalKacamataAction.class);
    private MedicalKacamataBo medicalKacamataBoProxy;
    private MedicalKacamata medicalKacamata;

    private List<MedicalKacamata> listComboGolongan = new ArrayList<MedicalKacamata>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MedicalKacamataAction.logger = logger;
    }

    public MedicalKacamataBo getMedicalKacamataBoProxy() {
        return medicalKacamataBoProxy;
    }

    public void setMedicalKacamataBoProxy(MedicalKacamataBo medicalKacamataBoProxy) {
        this.medicalKacamataBoProxy = medicalKacamataBoProxy;
    }

    public MedicalKacamata getMedicalKacamata() {
        return medicalKacamata;
    }

    public void setMedicalKacamata(MedicalKacamata medicalKacamata) {
        this.medicalKacamata = medicalKacamata;
    }

    public List<MedicalKacamata> getListComboGolongan() {
        return listComboGolongan;
    }

    public void setListComboGolongan(List<MedicalKacamata> listComboGolongan) {
        this.listComboGolongan = listComboGolongan;
    }

    public String initComboGolongan() {
        logger.info("[BranchAction.search] start process >>>");

        MedicalKacamata searchGolongan = new MedicalKacamata();
        List<MedicalKacamata> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = medicalKacamataBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
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

    public MedicalKacamata init(String kode, String flag){
        logger.info("[UpdateMedicalKacamataAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalKacamata> listOfResult = (List<MedicalKacamata>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MedicalKacamata golongan: listOfResult) {
                    /*if(kode.equalsIgnoreCase(golongan.getMedicalKacamataId()) && flag.equalsIgnoreCase(golongan.getFlag())){
                        setMedicalKacamata(golongan);
                        break;
                    }*/
                }
            } else {
                setMedicalKacamata(new MedicalKacamata());
            }

            logger.info("[UpdateMedicalKacamataAction.init] end process >>>");
        }
        return getMedicalKacamata();
    }

    @Override
    public String add() {
        logger.info("[UpdateMedicalKacamataAction.add] start process >>>");
        MedicalKacamata addMedical = new MedicalKacamata();
        setMedicalKacamata(addMedical);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[UpdateMedicalKacamataAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[UpdateMedicalKacamataAction.edit] start process >>>");
        String itemId = getId();
        int periode = Integer.parseInt(getPeriode()) - 1;

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalKacamata> listDataGolongan = (List<MedicalKacamata>) session.getAttribute("listDataGolongan");

        MedicalKacamata editGolongan = new MedicalKacamata();

        try {
            //session.setAttribute("dataPayroll", payroll);
            if(listDataGolongan == null){

            }

            session.setAttribute("listDataGolongan", listDataGolongan);
            editGolongan = init(itemId, "Y");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "UpdateGolonganBO.getGolonganByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalKacamataAction.edit] Error when retrieving edit data,", e1);
            }
            logger.error("[UpdateMedicalKacamataAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            return "failure";
        }

        if(editGolongan != null) {
            setMedicalKacamata(editGolongan);
        } else {
            //editGolongan.setMedicalKacamataId(itemId);
            setMedicalKacamata(editGolongan);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[UpdateMedicalKacamataAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MedicalKacamataAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MedicalKacamata deleteGolongan = new MedicalKacamata();

        if (itemFlag != null ) {

            try {
                deleteGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalKacamataAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MedicalKacamataAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteGolongan != null) {
                setMedicalKacamata(deleteGolongan);

            } else {
                //deleteGolongan.setMedicalKacamataId(itemId);
                deleteGolongan.setFlag(itemFlag);
                setMedicalKacamata(deleteGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deleteGolongan.setMedicalKacamataId(itemId);
            deleteGolongan.setFlag(itemFlag);
            setMedicalKacamata(deleteGolongan);
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
        logger.info("[MedicalKacamataAction.saveEdit] start process >>>");
        try {

            MedicalKacamata editGolongan = getMedicalKacamata();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            medicalKacamataBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalKacamataAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MedicalKacamataAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MedicalKacamataAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MedicalKacamataAction.saveDelete] start process >>>");
        try {

            MedicalKacamata deleteGolongan = getMedicalKacamata();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            medicalKacamataBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.saveDelete");
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

    @Override
    public String search() {
        logger.info("[MedicalKacamataAction.search] start process >>>");

        MedicalKacamata searchGolongan = getMedicalKacamata();
        List<MedicalKacamata> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = medicalKacamataBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalKacamataAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MedicalKacamataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
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
        logger.info("[MedicalKacamataAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listDataGolongan");
        logger.info("[MedicalKacamataAction.initForm] end process >>>");
        return INPUT;
    }


    public String paging(){
        return SUCCESS;
    }

    public String printGolongan(){
        String id = getId();
        int periode = Integer.parseInt(getPeriode()) - 1;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo updateGolonganBo = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");


        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Report Kenaikan Golongan");

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId =medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "searchReport");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.searchReport] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.searchReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }

        return "print_report_golongan";
    }

    public void saveAdd(String nip, String branchId, String divisiId, String bagianId, String golonganId, String positionId, String tipe,
                        String tanggal, String jumlahBiaya){

        MedicalKacamata medicalKacamata = new MedicalKacamata();
        medicalKacamata.setNip(nip);
        medicalKacamata.setBranchId(branchId);
        medicalKacamata.setBidangId(divisiId);
        medicalKacamata.setBagianId(bagianId);
        medicalKacamata.setGolonganId(golonganId);
        medicalKacamata.setPositionId(positionId);
        medicalKacamata.setTipePembayaran(tipe);
        medicalKacamata.setTanggalPembayaran(CommonUtil.convertStringToDate(tanggal));
        medicalKacamata.setBiaya(BigDecimal.valueOf(Double.parseDouble(jumlahBiaya)));

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        medicalKacamata.setCreatedWho(userLogin);
        medicalKacamata.setLastUpdateWho(userLogin);
        medicalKacamata.setCreatedDate(updateTime);
        medicalKacamata.setLastUpdate(updateTime);
        medicalKacamata.setFlag("Y");
        medicalKacamata.setAction("C");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo kacamataBo = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");
        kacamataBo.saveAddData(medicalKacamata);
    }

    public void saveEdit(String id, String nip, String branchId, String divisiId, String bagianId, String golonganId, String positionId, String tipe,
                        String tanggal, String jumlahBiaya){

        MedicalKacamata medicalKacamata = new MedicalKacamata();
        medicalKacamata.setMedicalKacamataId(id);
        medicalKacamata.setNip(nip);
        medicalKacamata.setBranchId(branchId);
        medicalKacamata.setBidangId(divisiId);
        medicalKacamata.setBagianId(bagianId);
        medicalKacamata.setGolonganId(golonganId);
        medicalKacamata.setPositionId(positionId);
        medicalKacamata.setTipePembayaran(tipe);
        medicalKacamata.setTanggalPembayaran(CommonUtil.convertStringToDate(tanggal));
        medicalKacamata.setBiaya(BigDecimal.valueOf(Double.parseDouble(jumlahBiaya)));

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        medicalKacamata.setCreatedWho(userLogin);
        medicalKacamata.setLastUpdateWho(userLogin);
        medicalKacamata.setCreatedDate(updateTime);
        medicalKacamata.setLastUpdate(updateTime);
        medicalKacamata.setFlag("Y");
        medicalKacamata.setAction("C");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo kacamataBo = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");
        kacamataBo.saveEditData(medicalKacamata);
    }

    public String cekTersediaMedicalKacamata(String nip){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo kacamataBo = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");
        String hasil = kacamataBo.cekMedical(nip);

        return hasil;
    }

    public String cekTersediaMedicalKacamata(String id, String nip){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo kacamataBo = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");
        String hasil = kacamataBo.cekMedical(id, nip);

        return hasil;
    }

    public void saveDeleteKacamata(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo kacamataBo = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");
        kacamataBo.saveDeleteKacamata(id);
    }

    public MedicalKacamata getDataKacamataById(String kacamataId){
        logger.info("[MedicalBiayaKacamata.getDataKacamataById] start process >>>");
        MedicalKacamata medicalKacamata = new MedicalKacamata();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalKacamataBo medicalKacamataBoProxy = (MedicalKacamataBo) ctx.getBean("medicalKacamataBoProxy");

        medicalKacamata = medicalKacamataBoProxy.getDataKacamataById(kacamataId);

        logger.info("[MedicalKacamata.getDataKacamataById] end process >>>");
        return medicalKacamata;
    }

    public String searchReport(){
        logger.info("[MedicalKacamata.searchReport] start process >>>");

        String kacamataId = getId();

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("kacamataId", kacamataId);
        reportParams.put("titleReport", "Report Medical Kacamata");

        if(kacamataId != null){
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = medicalKacamataBoProxy.saveErrorMessage(e.getMessage(), "searchReport");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.searchReport] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.searchReportDraft] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
        }

        logger.info("[MedicalKacamata.searchReport] end process <<<");
        return "print_report_medical_kacamata";
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

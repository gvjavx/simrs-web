package com.neurix.hris.transaksi.pendapatanDokter.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.pendapatanDokter.bo.PendapatanDokterBo;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterEntity;
import com.neurix.hris.transaksi.pendapatanDokter.model.PendapatanDokter;
import org.apache.batik.css.parser.Scanner;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.method.P;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PendapatanDokterAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PendapatanDokterAction.class);
    PendapatanDokter pendapatanDokter;
    PendapatanDokterBo pendapatanDokterBoProxy;
    BillingSystemBo billingSystemBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PendapatanDokterAction.logger = logger;
    }

    public BillingSystemBo getBillingSystemBoProxy() {
        return billingSystemBoProxy;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public PendapatanDokter getPendapatanDokter() {
        return pendapatanDokter;
    }

    public void setPendapatanDokter(PendapatanDokter pendapatanDokter) {
        this.pendapatanDokter = pendapatanDokter;
    }

    public PendapatanDokterBo getPendapatanDokterBoProxy() {
        return pendapatanDokterBoProxy;
    }

    public void setPendapatanDokterBoProxy(PendapatanDokterBo pendapatanDokterBoProxy) {
        this.pendapatanDokterBoProxy = pendapatanDokterBoProxy;
    }

    @Override
    public String add() {
        logger.info("[PedapatanDokterAction.add] start process >>>>");
        PendapatanDokter pendapatanDokter = new PendapatanDokter();

        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null)
            pendapatanDokter.setBranchId(branchId);
        else
            pendapatanDokter.setBranchId("");

        setPendapatanDokter(pendapatanDokter);
        setAddOrEdit(true);
        setAdd(true);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPendapatanDokter");

        logger.info("[PendapatanDokterAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {

        return "";
    }

    @Override
    public String delete() {

        return "";
    }

    @Override
    public String view() {

        return "";
    }

    @Override
    public String save() {
        logger.info("[PendapatanDokter.initForm] start process >>>");
        PendapatanDokter pendapatanDokter = new PendapatanDokter();
        String userLogin = CommonUtil.userLogin();
        java.sql.Timestamp updateTime = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        pendapatanDokter.setCreatedWho(userLogin);
        pendapatanDokter.setCreatedDate(updateTime);
        pendapatanDokter.setLastUpdateWho(userLogin);
        pendapatanDokter.setLastUpdate(updateTime);
        pendapatanDokter.setAction("C");
        pendapatanDokter.setFlag("Y");

        List<PendapatanDokter> savePendapatan ;
        try{
            savePendapatan = pendapatanDokterBoProxy.saveAddPendapatanDokter(pendapatanDokter);

            //menyimpan ke billing
            for (PendapatanDokter billing :savePendapatan){
                billingSystemBoProxy.createJurnal("44",billing.getDataBilling(),billing.getBranchId(),billing.getKeteranganBilling(),"Y");
            }

        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = pendapatanDokterBoProxy.saveErrorMessage(e.getMessage(), "pendapatanDokterBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[PendapatanDokterAction.save] Error when saving error,", e1);;
            }
            logger.error("[PendapatanDokterAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }

        return null;
    }

    @Override
    public String search() {
        logger.info("[PendapatanDokter.search] start process >>>");
        PendapatanDokter searchPendapatanDokter = getPendapatanDokter();
        List<PendapatanDokter> listOfSearchPendapatanDokter = new ArrayList<>();

        try{
            listOfSearchPendapatanDokter = pendapatanDokterBoProxy.getByCriteria(searchPendapatanDokter);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = pendapatanDokterBoProxy.saveErrorMessage(e.getMessage(), "pendapatanDokterBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PendapatanDokterAction.search] Error when saving error,", e1);;
            }
            logger.error("[PendapatanDokterAction.search] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        String branchId = CommonUtil.userBranchLogin();
        PendapatanDokter data = new PendapatanDokter();
        if (branchId != null){
            data.setBranchId(branchId);
        }else {
            data.setBranchId("");
        }

        pendapatanDokter = data;

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPendapatanDokter");
        session.setAttribute("listOfResultPendapatanDokter", listOfSearchPendapatanDokter);

        logger.info("[PendapatanDokterAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PendapatanDokter.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PendapatanDokter data = new PendapatanDokter();
        if (branchId != null){
            data.setBranchId(branchId);
        }else {
            data.setBranchId("");
        }

        pendapatanDokter = data;

        session.removeAttribute("listOfResultPendapatanDokter");
        logger.info("[PendapatanDokterAction.initForm] end process >>>");
        return INPUT;
    }

    @Override
    public String downloadPdf() {


        return "";
    }

    @Override
    public String downloadXls() {

        return "";
    }

//    public List<PendapatanDokter> searchDetailPendapatan(String branchId, String bulan, String tahun){
//        logger.info("[PendapatanDokterAction.pendapatan] start process >>>");
//
//        PendapatanDokter pendapatanDokter = new PendapatanDokter();
//        pendapatanDokter.setBranchId(branchId);
//        pendapatanDokter.setBulan(bulan);
//        pendapatanDokter.setTahun(tahun);
//        List<PendapatanDokter> pendapatanDokterEntityList = new ArrayList<>();
//
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        PendapatanDokterBo pendapatanDokterBo = (PendapatanDokterBo) ctx.getBean("pendapatanDokterBoProxy");
//        try{
//            pendapatanDokterEntityList = pendapatanDokterBo.getByCriteriaForPendapatanDokter(pendapatanDokter);
//        }catch (GeneralBOException e){
//            Long logId = null;
//            try {
//                logId = pendapatanDokterBoProxy.saveErrorMessage(e.getMessage(), "pendapatanDokterBO.getByCriteriaForPendapatanDokter");
//            } catch (GeneralBOException e1) {
//                logger.error("[PendapatanDokterAction.pendapatan] Error when saving error,", e1);;
//            }
//            logger.error("[PendapatanDokterAction.pendapatan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//        }
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResultDetailPendapatanDokter");
//        session.setAttribute("listOfResultDetailPendapatanDokter", pendapatanDokterEntityList);
//        return pendapatanDokterEntityList;
//    }

    public List<PendapatanDokter> getDetailPendapatan(String pendapatanDokterId){
        logger.info("[PendapatanDokterAction.getDetailPendapatan] start process >>>");

        PendapatanDokter searchPendapatanDokter = new PendapatanDokter();
        searchPendapatanDokter.setPendapatanDokterId(pendapatanDokterId);
        List<PendapatanDokter> listOfSearchPendapatanDetail = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendapatanDokterBo pendapatanDokterBo = (PendapatanDokterBo) ctx.getBean("pendapatanDokterBoProxy");
        try{
            listOfSearchPendapatanDetail = pendapatanDokterBo.getDetailPendapatan(searchPendapatanDokter);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = pendapatanDokterBo.saveErrorMessage(e.getMessage(), "pendapatanDokterBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PendapatanDokterAction.search] Error when saving error,", e1);;
            }
            logger.error("[PendapatanDokterAction.search] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

//        for (PendapatanDokter pendapatanDokter : listOfSearchPendapatanDetail){
//            PendapatanDokter dokter = new PendapatanDokter();
//            dokter.setNoReg(pendapatanDokter.getNoReg());
//            dokter.setJenisRawat(pendapatanDokter.getJenisRawat());
//            dokter.setKdjnspas(pendapatanDokter.getKdjnspas());
//            dokter.setNamaPasien(pendapatanDokter.getNamaPasien());
//            dokter.setTanggal(pendapatanDokter.getTanggal());
//            dokter.setKeterangan(pendapatanDokter.getKeterangan());
//            dokter.setTarifInacbg(pendapatanDokter.getTarifInacbg());
//            dokter.setBruto(pendapatanDokter.getBruto());
//            dokter.setPendapatanRs(pendapatanDokter.getPendapatanRs());
//            dokter.setHrBruto(pendapatanDokter.getHrBruto());
//            dokter.setDppPph21(pendapatanDokter.getDppPph21());
//            dokter.setDppPph21Komulatif(pendapatanDokter.getDppPph21Komulatif());
//            dokter.setTarif(pendapatanDokter.getTarif());
//            dokter.setPphDipungut(pendapatanDokter.getPphDipungut());
//            dokter.setHrAktifitasNetto(pendapatanDokter.getHrAktifitasNetto());
//            dokter.setPotKs(pendapatanDokter.getPotKs());
//            dokter.setGajiBersih(pendapatanDokter.getGajiBersih());
//            dokter.setMasterId(pendapatanDokter.getMasterId());
//            dokter.setPoliName(pendapatanDokter.getPoliName());
//            dokter.setActivityName(pendapatanDokter.getActivityName());
//            detailPendapatanList.add(dokter);
//        }

        return listOfSearchPendapatanDetail;
    }

    public List<PendapatanDokter> searchDetailPendapatan(String dokterId){
        logger.info("[PendapatanDokterAction.pendapatan] start process >>>");

        List<PendapatanDokter> pendapatanDokterEntityList = new ArrayList<>();
        List<PendapatanDokter> detailPendapatanList = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        pendapatanDokterEntityList = (List<PendapatanDokter>) session.getAttribute("listOfResultDetailPendapatanDokter");
        for (PendapatanDokter pendapatanDokter : pendapatanDokterEntityList){
            if (pendapatanDokter.getDokterId().equalsIgnoreCase(dokterId)){
                PendapatanDokter dokter = new PendapatanDokter();
                dokter.setNoReg(pendapatanDokter.getNoReg());
                dokter.setJenisRawat(pendapatanDokter.getJenisRawat());
                dokter.setKdjnspas(pendapatanDokter.getKdjnspas());
                dokter.setNamaPasien(pendapatanDokter.getNamaPasien());
                dokter.setTanggal(pendapatanDokter.getTanggal());
                dokter.setKeterangan(pendapatanDokter.getKeterangan());
                dokter.setTarifInacbg(pendapatanDokter.getTarifInacbg());
                dokter.setBruto(pendapatanDokter.getBruto());
                dokter.setPendapatanRs(pendapatanDokter.getPendapatanRs());
                dokter.setHrBruto(pendapatanDokter.getHrBruto());
                dokter.setDppPph21(pendapatanDokter.getDppPph21());
                dokter.setDppPph21Komulatif(pendapatanDokter.getDppPph21Komulatif());
                dokter.setTarif(pendapatanDokter.getTarif());
                dokter.setPphDipungut(pendapatanDokter.getPphDipungut());
                dokter.setHrAktifitasNetto(pendapatanDokter.getHrAktifitasNetto());
                dokter.setPotKs(pendapatanDokter.getPotKs());
                dokter.setGajiBersih(pendapatanDokter.getGajiBersih());
                dokter.setMasterId(pendapatanDokter.getMasterId());
                dokter.setPoliName(pendapatanDokter.getPoliName());
                dokter.setActivityName(pendapatanDokter.getActivityName());
                detailPendapatanList.add(dokter);
            }
        }

        return detailPendapatanList;
    }



    public void pendapatan() throws Exception{
        logger.info("[PendapatanDokterAction.pendapatan] start process >>>");

        PendapatanDokter pendapatanDokter = getPendapatanDokter();
        List<PendapatanDokter> pendapatanDokterEntityList = new ArrayList<>();
        List<PendapatanDokter> pendapatanDokterDetailEntityList = new ArrayList<>();

        try{
            pendapatanDokterEntityList = pendapatanDokterBoProxy.getDataPendapatanDokter(pendapatanDokter);
            pendapatanDokterDetailEntityList = pendapatanDokterBoProxy.getByCriteriaForPendapatanDokter(pendapatanDokter);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = pendapatanDokterBoProxy.saveErrorMessage(e.getMessage(), "pendapatanDokterBO.getByCriteriaForPendapatanDokter");
            } catch (GeneralBOException e1) {
                logger.error("[PendapatanDokterAction.pendapatan] Error when saving error,", e1);;
            }
            logger.error("[PendapatanDokterAction.pendapatan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPendapatanDokter");
        session.setAttribute("listOfResultPendapatanDokter", pendapatanDokterEntityList);
        session.removeAttribute("listOfResultDetailPendapatanDokter");
        session.setAttribute("listOfResultDetailPendapatanDokter", pendapatanDokterDetailEntityList);
    }

    public String goToResult(){
        return "go_to_result";
    }

    public List<PendapatanDokter> loadResultsPendapatan(){
        List<PendapatanDokter> pendapatanDokters = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        pendapatanDokters = (List<PendapatanDokter>) session.getAttribute("listOfResultPendapatanDokter");

        return pendapatanDokters;
    }

}
package com.neurix.akuntansi.transaksi.pengajuanSetor.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.PengajuanSetorBo;
import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.impl.PengajuanSetorBoImpl;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.*;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PengajuanSetorAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PengajuanSetorAction.class);
    private PengajuanSetorBo pengajuanSetorBoProxy;
    private PengajuanSetor pengajuanSetor;
    private PerhitunganPpnKd perhitunganPpnKd;
    private PengajuanSetorDetail pengajuanSetorDetail;
    private List<PengajuanSetor> listOfComboPengajuanSetor = new ArrayList<PengajuanSetor>();
    private String bulan;
    private String tahun;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public PerhitunganPpnKd getPerhitunganPpnKd() {
        return perhitunganPpnKd;
    }

    public void setPerhitunganPpnKd(PerhitunganPpnKd perhitunganPpnKd) {
        this.perhitunganPpnKd = perhitunganPpnKd;
    }

    public PengajuanSetorDetail getPengajuanSetorDetail() {
        return pengajuanSetorDetail;
    }

    public void setPengajuanSetorDetail(PengajuanSetorDetail pengajuanSetorDetail) {
        this.pengajuanSetorDetail = pengajuanSetorDetail;
    }



    public List<PengajuanSetor> getListOfComboPengajuanSetor() {
        return listOfComboPengajuanSetor;
    }

    public void setListOfComboPengajuanSetor(List<PengajuanSetor> listOfComboPengajuanSetor) {
        this.listOfComboPengajuanSetor = listOfComboPengajuanSetor;
    }


    public PengajuanSetorBo getPengajuanSetorBoProxy() {
        return pengajuanSetorBoProxy;
    }

    public void setPengajuanSetorBoProxy(PengajuanSetorBo pengajuanSetorBoProxy) {
        this.pengajuanSetorBoProxy = pengajuanSetorBoProxy;
    }

    public PengajuanSetor getPengajuanSetor() {
        return pengajuanSetor;
    }

    public void setPengajuanSetor(PengajuanSetor pengajuanSetor) {
        this.pengajuanSetor = pengajuanSetor;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PengajuanSetorAction.logger = logger;
    }

    public PengajuanSetor init(String kode, String flag){
        logger.info("[PengajuanSetorAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanSetor> listOfResult = (List<PengajuanSetor>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PengajuanSetor pengajuanSetor: listOfResult) {
                    if(kode.equalsIgnoreCase(pengajuanSetor.getPengajuanSetorId()) && flag.equalsIgnoreCase(pengajuanSetor.getFlag())){
                        setPengajuanSetor(pengajuanSetor);
                        break;
                    }
                }
            } else {
                setPengajuanSetor(new PengajuanSetor());
            }

            logger.info("[PengajuanSetorAction.init] end process >>>");
        }
        return getPengajuanSetor();
    }

    public String initFormPengajuanSetorPph21() {
        logger.info("[PengajuanSetorAction.initFormPengajuanSetorPph21] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PengajuanSetor data = new PengajuanSetor();

        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }

        setPengajuanSetor(data);
        session.removeAttribute("listOfResult");
        logger.info("[PengajuanSetorAction.initFormPengajuanSetorPph21] end process >>>");
        return "pengajuan_setor_pph21";
    }

    public String initFormPengajuanSetorPpn() {
        logger.info("[PengajuanSetorAction.initFormPengajuanSetorPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PengajuanSetor data = new PengajuanSetor();

        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }

        setPengajuanSetor(data);
        session.removeAttribute("listOfResult");
        logger.info("[PengajuanSetorAction.initFormPengajuanSetorPpn] end process >>>");
        return "pengajuan_setor_ppn";
    }

    public String initFormProsesPpnKd() {
        logger.info("[PengajuanSetorAction.initFormProsesPpnKd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PengajuanSetor data = new PengajuanSetor();

        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }

        setPengajuanSetor(data);
        session.removeAttribute("listOfResult");
        logger.info("[PengajuanSetorAction.initFormProsesPpnKd] end process >>>");
        return "proses_ppn_kd";
    }

    public String addPengajuanSetorPpn() {
        logger.info("[PengajuanSetorAction.addPengajuanSetorPpn] start process >>>");
        PengajuanSetor addPengajuanSetor = new PengajuanSetor();
        String branchId = CommonUtil.userBranchLogin();

        if (branchId!=null){
            addPengajuanSetor.setBranchId(branchId);
        }else{
            addPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(addPengajuanSetor);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPengajuanSetorDetail");

        logger.info("[PengajuanSetorAction.addPengajuanSetorPpn] stop process >>>");
        return "add_pengajuan_setor_ppn";
    }

    public String addProsesPpnKantorPusat() {
        logger.info("[PengajuanSetorAction.addProsesPpnKantorPusat] start process >>>");
        PengajuanSetor addPengajuanSetor = new PengajuanSetor();
        String branchId = CommonUtil.userBranchLogin();

        if (branchId!=null){
            addPengajuanSetor.setBranchId(branchId);
        }else{
            addPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(addPengajuanSetor);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("resultPerhitunganPpnKd");
        session.removeAttribute("listOfResultHasilProsesPpnKd");

        logger.info("[PengajuanSetorAction.addProsesPpnKantorPusat] stop process >>>");
        return "add_proses_ppn_kanpus";
    }

    public String addPengajuanSetorPph21() {
        logger.info("[PengajuanSetorAction.addPengajuanSetorPph21] start process >>>");
        PengajuanSetor addPengajuanSetor = new PengajuanSetor();
        String branchId = CommonUtil.userBranchLogin();

        if (branchId!=null){
            addPengajuanSetor.setBranchId(branchId);
        }else{
            addPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(addPengajuanSetor);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPengajuanSetorDetail");

        logger.info("[PengajuanSetorAction.addPengajuanSetorPph21] stop process >>>");
        return "add_pengajuan_setor_pph21";
    }

    public String saveAddPengajuanSetorPph21(){
        logger.info("[PengajuanSetorAction.resultAddPengajuanSetorPph21] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor pengajuanSetor = getPengajuanSetor();
        List<PengajuanSetorDetail> pengajuanSetorDetailListPayroll = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataPayroll");
        List<PengajuanSetorDetail> pengajuanSetorDetailListKso = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKso");
        List<PengajuanSetorDetail> pengajuanSetorDetailListPengajuan = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataPengajuan");

        String username = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pengajuanSetor.setAction("C");
        pengajuanSetor.setFlag("Y");
        pengajuanSetor.setCreatedWho(username);
        pengajuanSetor.setLastUpdateWho(username);
        pengajuanSetor.setLastUpdate(time);
        pengajuanSetor.setCreatedDate(time);
        pengajuanSetor.setRegisteredDate(CommonUtil.convertStringToDate(pengajuanSetor.getStRegisteredDate()));

        try {
            pengajuanSetorBoProxy.saveAddPengajuanSetorPph21(pengajuanSetor,pengajuanSetorDetailListPayroll,pengajuanSetorDetailListKso,pengajuanSetorDetailListPengajuan);
        } catch (GeneralBOException e) {
            logger.error("[PengajuanSetorAction.saveAddPengajuanSetorPph21] Error when save : ", e);
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PengajuanSetorAction.resultAddPengajuanSetorPph21] stop process >>>");
        return "success_save_pengajuan_setor_pph21";
    }

    public String resultAddPengajuanSetorPph21(){
        logger.info("[PengajuanSetorAction.resultAddPengajuanSetorPph21] start process >>>");
        PengajuanSetor addPengajuanSetor = new PengajuanSetor();
        String branchId = CommonUtil.userBranchLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();
        addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        if (branchId!=null){
            addPengajuanSetor.setBranchId(branchId);
        }else{
            addPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(addPengajuanSetor);

        logger.info("[PengajuanSetorAction.resultAddPengajuanSetorPph21] stop process >>>");
        return "search_add_pengajuan_setor_pph21";
    }

    public String resultAddPengajuanSetorPpn(){
        logger.info("[PengajuanSetorAction.resultAddPengajuanSetorPpn] start process >>>");
        PengajuanSetor addPengajuanSetor = new PengajuanSetor();
        String branchId = CommonUtil.userBranchLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();
        addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");
        if (branchId!=null){
            addPengajuanSetor.setBranchId(branchId);
        }else{
            addPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(addPengajuanSetor);

        logger.info("[PengajuanSetorAction.resultAddPengajuanSetorPpn] stop process >>>");
        return "success_add_pemilihan_pengajuan_setor_ppn";
    }

    public String resultAddProsesPpnKanpus(){
        logger.info("[PengajuanSetorAction.resultAddProsesPpnKanpus] start process >>>");
//        PengajuanSetor addPengajuanSetor = new PengajuanSetor();
//        String branchId = CommonUtil.userBranchLogin();
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");
//        if (branchId!=null){
//            addPengajuanSetor.setBranchId(branchId);
//        }else{
//            addPengajuanSetor.setBranchId("");
//        }
//        setPengajuanSetor(addPengajuanSetor);

        logger.info("[PengajuanSetorAction.resultAddProsesPpnKanpus] stop process >>>");
        return "success_add_proses_ppn_kanpus";
    }

    public String resultAddTmpPengajuanSetorPpn(){
        logger.info("[PengajuanSetorAction.resultAddTmpPengajuanSetorPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor =  (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");
        setPengajuanSetor(addPengajuanSetor);

        logger.info("[PengajuanSetorAction.resultAddTmpPengajuanSetorPpn] stop process >>>");
        return "success_add_hasil_pengajuan_setor_ppn";
    }

    public String searchAddPengajuanSetorPph21() {
        logger.info("[PengajuanSetorAction.searchAddPengajuanSetorPph21] start process >>>");
        PengajuanSetor addPengajuanSetor = getPengajuanSetor();
        PengajuanSetor resultPengajuanSetor = new PengajuanSetor();
        resultPengajuanSetor.setJumlahPph21Payroll(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahPph21Kso(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahPph21Pengajuan(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahSeluruhnya(BigDecimal.ZERO);
        resultPengajuanSetor.setTahun(addPengajuanSetor.getTahun());
        resultPengajuanSetor.setBulan(addPengajuanSetor.getBulan());
        HttpSession session = ServletActionContext.getRequest().getSession();
        addPengajuanSetor.setBulan(addPengajuanSetor.getBulan());

        Integer tahunSebelumnya = Integer.parseInt(addPengajuanSetor.getTahun())-1;
        addPengajuanSetor.setStTanggalDari(String.valueOf(tahunSebelumnya)+"-12-01");
        addPengajuanSetor.setStTanggalSelesai(String.valueOf(addPengajuanSetor.getTahun())+"-"+addPengajuanSetor.getBulan()+"-01");

        List<PengajuanSetorDetail> pengajuanSetorDetailPayrollList = pengajuanSetorBoProxy.listPPhPayroll(addPengajuanSetor);
        List<PengajuanSetorDetail> pengajuanSetorDetailKsoList = pengajuanSetorBoProxy.listPPh21KsoDokter(addPengajuanSetor);
        List<PengajuanSetorDetail> pengajuanSetorDetailPengajuanList = pengajuanSetorBoProxy.listPPh21Pengajuan(addPengajuanSetor);

        //melakukan total payroll
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailPayrollList){
            resultPengajuanSetor.setJumlahPph21Payroll(resultPengajuanSetor.getJumlahPph21Payroll().add(pengajuanSetorDetail.getJumlah()));
        }

        //melakukan total kso
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailKsoList){
            resultPengajuanSetor.setJumlahPph21Kso(resultPengajuanSetor.getJumlahPph21Kso().add(pengajuanSetorDetail.getJumlah()));
        }

        //melakukan total pengajuan
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailPengajuanList){
            resultPengajuanSetor.setJumlahPph21Pengajuan(resultPengajuanSetor.getJumlahPph21Pengajuan().add(pengajuanSetorDetail.getJumlah()));
        }

        resultPengajuanSetor.setJumlahSeluruhnya(resultPengajuanSetor.getJumlahPph21Payroll().add(resultPengajuanSetor.getJumlahPph21Kso()).add(resultPengajuanSetor.getJumlahPph21Pengajuan()));

        //set string
        resultPengajuanSetor.setStJumlahPph21Payroll(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPph21Payroll(),"###,###"));
        resultPengajuanSetor.setStJumlahPph21Kso(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPph21Kso(),"###,###"));
        resultPengajuanSetor.setStJumlahPph21Pengajuan(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPph21Pengajuan(),"###,###"));
        resultPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahSeluruhnya(),"###,###"));

        session.removeAttribute("listOfResultPencarianDataPayroll");
        session.removeAttribute("listOfResultPengajuanSetor");
        session.removeAttribute("listOfResultPencarianDataKso");
        session.removeAttribute("listOfResultPencarianDataPengajuan");
        session.setAttribute("listOfResultPencarianDataPayroll", pengajuanSetorDetailPayrollList);
        session.setAttribute("listOfResultPencarianDataKso", pengajuanSetorDetailKsoList);
        session.setAttribute("listOfResultPencarianDataPengajuan", pengajuanSetorDetailPengajuanList);
        session.setAttribute("listOfResultPengajuanSetor", resultPengajuanSetor);

        logger.info("[PengajuanSetorAction.searchAddPengajuanSetorPph21] stop process >>>");
        return "success_add_pengajuan_setor_pph21";
    }

    private String getSearchWhereBulan(String bulan){
        String result ="";

        switch (bulan){
            case "12":
                result = "'11','10','09','08','07','06','05','04','03','02','01'";
                break;
            case "11":
                result = "'10','09','08','07','06','05','04','03','02','01'";
                break;
            case "10":
                result = "'09','08','07','06','05','04','03','02','01'";
                break;
            case "09":
                result = "'08','07','06','05','04','03','02','01'";
                break;
            case "08":
                result = "'07','06','05','04','03','02','01'";
                break;
            case "07":
                result = "'06','05','04','03','02','01'";
                break;
            case "06":
                result = "'05','04','03','02','01'";
                break;
            case "05":
                result = "'04','03','02','01'";
                break;
            case "04":
                result = "'03','02','01'";
                break;
            case "03":
                result = "'02','01'";
                break;
            case "02":
                result = "'01'";
                break;
            case "01":
                result = "''";
                break;
        }
        return result;
    }

    public List<PengajuanSetorDetail> searchDataSessionPph21Payroll() {
        logger.info("[PengajuanSetorAction.searchDataSessionPph21Payroll] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPph21Payroll] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataPayroll");
    }

    public List<PengajuanSetorDetail> searchDataSessionPph21Kso() {
        logger.info("[PengajuanSetorAction.searchDataSessionPph21Kso] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPph21Kso] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKso");
    }

    public List<PengajuanSetorDetail> searchDataSessionPph21Pengajuan() {
        logger.info("[PengajuanSetorAction.searchDataSessionPph21Pengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPph21Pengajuan] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataPengajuan");
    }

    public String searchPengajuanSetorPph21() {
        logger.info("[PengajuanBiayaAction.searchPengajuanSetorPph21] start process >>>");
        PengajuanSetor searchPengajuanSetor = getPengajuanSetor();
        List<PengajuanSetor> listOfsearchPengajuanSetor = new ArrayList();
        searchPengajuanSetor.setTipePengajuanSetor("PPH21");
        try {
            listOfsearchPengajuanSetor = pengajuanSetorBoProxy.getByCriteria(searchPengajuanSetor);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.searchPengajuanSetorPph21");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.searchPengajuanSetorPph21] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PengajuanBiayaAction.searchPengajuanSetorPph21] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPengajuanSetor);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPengajuanSetor.setBranchId(branchId);
        }else{
            searchPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(searchPengajuanSetor);
        logger.info("[PengajuanBiayaAction.searchPengajuanSetorPph21] end process <<<");

        return "success_pengajuan_setor_pph21";
    }

    public String searchPengajuanSetorPpn() {
        logger.info("[PengajuanBiayaAction.searchPengajuanSetorPpn] start process >>>");
        PengajuanSetor searchPengajuanSetor = getPengajuanSetor();
        List<PengajuanSetor> listOfsearchPengajuanSetor = new ArrayList();
        searchPengajuanSetor.setTipePengajuanSetor("PPN");

        try {
            listOfsearchPengajuanSetor = pengajuanSetorBoProxy.getByCriteria(searchPengajuanSetor);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.searchPengajuanSetorPpn");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.searchPengajuanSetorPpn] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PengajuanBiayaAction.searchPengajuanSetorPpn] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPengajuanSetor);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPengajuanSetor.setBranchId(branchId);
        }else{
            searchPengajuanSetor.setBranchId("");
        }
        setPengajuanSetor(searchPengajuanSetor);
        logger.info("[PengajuanBiayaAction.searchPengajuanSetorPpn] end process <<<");

        return "success_pengajuan_setor_ppn";
    }

    public PengajuanSetor editSessionPayroll(String id,boolean check) {
        logger.info("[PengajuanSetorAction.editSessionPayroll] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        List<PengajuanSetorDetail> pengajuanSetorDetailList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataPayroll");
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
            if (id.equalsIgnoreCase(pengajuanSetorDetail.getTransaksiId())){
                if (check){
                    pengajuanSetorDetail.setDibayar("Y");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().add(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPph21Payroll(addPengajuanSetor.getJumlahPph21Payroll().add(pengajuanSetorDetail.getJumlah()));
                }else{
                    pengajuanSetorDetail.setDibayar("N");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().subtract(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPph21Payroll(addPengajuanSetor.getJumlahPph21Payroll().subtract(pengajuanSetorDetail.getJumlah()));
                }
            }
        }
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));
        addPengajuanSetor.setStJumlahPph21Payroll(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPph21Payroll(),"###,###"));

        logger.info("[PengajuanSetorAction.editSessionPayroll] stop process >>>");

        session.setAttribute("listOfResultPengajuanSetor", addPengajuanSetor);
        session.setAttribute("listOfResultPencarianDataPayroll", pengajuanSetorDetailList);

        return addPengajuanSetor;
    }

    public PengajuanSetor editSessionKso(String id,boolean check) {
        logger.info("[PengajuanSetorAction.editSessionKso] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        List<PengajuanSetorDetail> pengajuanSetorDetailList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKso");
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
            if (id.equalsIgnoreCase(pengajuanSetorDetail.getTransaksiId())){
                if (check){
                    pengajuanSetorDetail.setDibayar("Y");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().add(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPph21Kso(addPengajuanSetor.getJumlahPph21Kso().add(pengajuanSetorDetail.getJumlah()));
                }else{
                    pengajuanSetorDetail.setDibayar("N");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().subtract(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPph21Kso(addPengajuanSetor.getJumlahPph21Kso().subtract(pengajuanSetorDetail.getJumlah()));
                }
            }
        }
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));
        addPengajuanSetor.setStJumlahPph21Kso(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPph21Kso(),"###,###"));

        logger.info("[PengajuanSetorAction.editSessionKso] stop process >>>");

        session.setAttribute("listOfResultPengajuanSetor", addPengajuanSetor);
        session.setAttribute("listOfResultPencarianDataKso", pengajuanSetorDetailList);

        return addPengajuanSetor;
    }

    public PengajuanSetor editSessionPengajuan(String id,boolean check) {
        logger.info("[PengajuanSetorAction.editSessionPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        List<PengajuanSetorDetail> pengajuanSetorDetailList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataPengajuan");
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
            if (id.equalsIgnoreCase(pengajuanSetorDetail.getTransaksiId())){
                if (check){
                    pengajuanSetorDetail.setDibayar("Y");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().add(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPph21Pengajuan(addPengajuanSetor.getJumlahPph21Pengajuan().add(pengajuanSetorDetail.getJumlah()));
                }else{
                    pengajuanSetorDetail.setDibayar("N");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().subtract(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPph21Pengajuan(addPengajuanSetor.getJumlahPph21Pengajuan().subtract(pengajuanSetorDetail.getJumlah()));
                }
            }
        }
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));
        addPengajuanSetor.setStJumlahPph21Pengajuan(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPph21Pengajuan(),"###,###"));

        logger.info("[PengajuanSetorAction.editSessionPengajuan] stop process >>>");

        session.setAttribute("listOfResultPengajuanSetor", addPengajuanSetor);
        session.setAttribute("listOfResultPencarianDataPengajuan", pengajuanSetorDetailList);

        return addPengajuanSetor;
    }


    public PerhitunganPpnKd getModalPostingPpn (String bulan , String tahun){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");

        return pengajuanSetorBo.getModalPostingPpn(bulan,tahun);
    }

    public PengajuanSetor getForModalPopUp(String pengajuanSetorId) {
        logger.info("[PengajuanSetorAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
        String itemFlag = "Y";
        PengajuanSetor modalPopUp = new PengajuanSetor();
        List<PengajuanSetorDetail> listDetailPayroll = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailKso = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailPengajuan = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailMasukanB2 = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailKeluaran = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailMasukanB3 = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(pengajuanSetorId, itemFlag);
            if (modalPopUp!=null){
                listDetailPayroll = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetorId,"Payroll");
                listDetailKso = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetorId,"Dokter KSO");
                listDetailPengajuan = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetorId,"Pengajuan Biaya PPH21");

                listDetailMasukanB2 = pengajuanSetorBo.getDetailPengajuanSetorPPn(pengajuanSetorId,"PPN Masukan B2");
                listDetailKeluaran = pengajuanSetorBo.getDetailPengajuanSetorPPn(pengajuanSetorId,"PPN Keluaran");
                listDetailMasukanB3 = pengajuanSetorBo.getDetailPengajuanSetorPPn(pengajuanSetorId,"PPN Masukan B3");

                session.setAttribute("listOfResultPencarianDataPayroll",listDetailPayroll);
                session.setAttribute("listOfResultPencarianDataKso",listDetailKso);
                session.setAttribute("listOfResultPencarianDataPengajuan",listDetailPengajuan);
                session.setAttribute("listOfResultPencarianDataMasukanB2", listDetailMasukanB2);
                session.setAttribute("listOfResultPencarianDataKeluaran", listDetailKeluaran);
                session.setAttribute("listOfResultPencarianDataMasukanB3", listDetailMasukanB3);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBo.saveErrorMessage(e.getMessage(), "PengajuanSetorAction.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanSetorAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[PengajuanSetorAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        return modalPopUp;
    }

    public String postingJurnal(String pengajuanSetorId){
        logger.info("[PengajuanSetorAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
            BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

            PengajuanSetor data = new PengajuanSetor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ItPengajuanSetorEntity pengajuanSetor = pengajuanSetorBo.getPengajuanSetorById(pengajuanSetorId);

            Map dataPostingJurnal = pengajuanSetorBo.getBillingForPosting(pengajuanSetorId);
            //disini untuk posting jurnal untuk mendapat nojurnal
            Jurnal jurnal = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PENGAJUAN_SETOR_PPH21,dataPostingJurnal,pengajuanSetor.getBranchId(),pengajuanSetor.getKeterangan(),"Y");
            data.setPengajuanSetorId(pengajuanSetorId);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalId(userLogin);
            data.setPostingDate(updateTime);
            data.setPostingFlag("Y");
            data.setPostingId(userLogin);
            data.setNoJurnal(jurnal.getNoJurnal());
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            pengajuanSetorBo.approvePengajuanSetor(data);
            pengajuanSetorBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanSetorAction.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanSetorAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanSetorAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanSetorAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String approvePengajuanSetorPpn(String pengajuanSetorId){
        logger.info("[PengajuanSetorAction.approvePengajuanSetorPpn] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");

            PengajuanSetor data = new PengajuanSetor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setPengajuanSetorId(pengajuanSetorId);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalId(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            pengajuanSetorBo.approvePengajuanSetor(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanSetorAction.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanSetorAction.approvePengajuanSetorPpn] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanSetorAction.approvePengajuanSetorPpn] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanSetorAction.approvePengajuanSetorPpn] end process <<<");

        return "Sukses Approve Pengajuan Setor PPN";
    }

    public String batalkanPengajuan(String pengajuanSetorId){
        logger.info("[PengajuanSetorAction.batalkanPengajuan] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
            PengajuanSetor data = new PengajuanSetor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setPengajuanSetorId(pengajuanSetorId);
            data.setCancelDate(updateTime);
            data.setCancelFlag("Y");
            data.setCancelId(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            pengajuanSetorBo.batalkanPengajuan(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanSetorAction.batalkanPengajuan");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanSetorAction.batalkanPengajuan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanSetorAction.batalkanPengajuan] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanSetorAction.batalkanPengajuan] end process <<<");

        return "Berhasil Membatalkan Pengajuan Setor";
    }

    public String searchAddPengajuanSetorPpn() {
        logger.info("[PengajuanSetorAction.searchAddPengajuanSetorPpn] start process >>>");
        PengajuanSetor addPengajuanSetor = getPengajuanSetor();
        PengajuanSetor resultPengajuanSetor = new PengajuanSetor();
        resultPengajuanSetor.setJumlahPpnMasukanB2(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahPpnKeluaran(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahPpnMasukanB3(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahSeluruhnya(BigDecimal.ZERO);
        resultPengajuanSetor.setTahun(addPengajuanSetor.getTahun());
        resultPengajuanSetor.setBulan(addPengajuanSetor.getBulan());
        HttpSession session = ServletActionContext.getRequest().getSession();
        addPengajuanSetor.setPeriode(addPengajuanSetor.getBulan()+"/"+addPengajuanSetor.getTahun());
        addPengajuanSetor.setBulan(addPengajuanSetor.getBulan());

        Integer tahunSebelumnya = Integer.parseInt(addPengajuanSetor.getTahun())-1;
        addPengajuanSetor.setStTanggalDari(String.valueOf(tahunSebelumnya)+"-12-01");
        addPengajuanSetor.setStTanggalSelesai(String.valueOf(addPengajuanSetor.getTahun())+"-"+addPengajuanSetor.getBulan()+"-01");

        List<PengajuanSetorDetail> pengajuanSetorDetailKeluaranList = pengajuanSetorBoProxy.listPPnKeluaran(addPengajuanSetor);
        List<PengajuanSetorDetail> pengajuanSetorDetailMasukanB2List = pengajuanSetorBoProxy.listPPnMasukan(addPengajuanSetor);

//        melakukan total keluaran
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailKeluaranList){
            resultPengajuanSetor.setJumlahPpnKeluaran(resultPengajuanSetor.getJumlahPpnKeluaran().add(pengajuanSetorDetail.getJumlah()));
        }

        resultPengajuanSetor.setJumlahSeluruhnya(resultPengajuanSetor.getJumlahPpnKeluaran());
        resultPengajuanSetor.setStJumlahPpnMasukanB2(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPpnMasukanB2(),"###,###"));
        resultPengajuanSetor.setStJumlahPpnMasukanB3(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPpnMasukanB3(),"###,###"));
        resultPengajuanSetor.setStJumlahPpnKeluaran(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPpnKeluaran(),"###,###"));
        resultPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahSeluruhnya(),"###,###"));

        session.setAttribute("listOfResultPencarianDataMasukan", pengajuanSetorDetailMasukanB2List);
        session.setAttribute("listOfResultPencarianDataKeluaran", pengajuanSetorDetailKeluaranList);
        session.setAttribute("listOfResultPengajuanSetor", resultPengajuanSetor);

        logger.info("[PengajuanSetorAction.searchAddPengajuanSetorPpn] stop process >>>");
        return "success_add_pengajuan_setor_ppn";
    }

    public List<PengajuanSetorDetail> searchDataSessionPpnMasukan() {
        logger.info("[PengajuanSetorAction.searchDataSessionPpnMasukan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPpnMasukan] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukan");
    }

    public List<PengajuanSetorDetail> searchDataSessionPpnMasukanB2() {
        logger.info("[PengajuanSetorAction.searchDataSessionPpnMasukanB2] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPpnMasukanB2] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukanB2");
    }

    public List<PengajuanSetorDetail> searchDataSessionPpnMasukanB3() {
        logger.info("[PengajuanSetorAction.searchDataSessionPpnMasukanB3] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPpnMasukanB3] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukanB3");
    }

    public List<PengajuanSetorDetail> searchDataSessionPpnKeluaran() {
        logger.info("[PengajuanSetorAction.searchDataSessionPpnKeluaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPpnKeluaran] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");
    }

    public List<ProsesPpnKd> searchDataSessionProsesPpn() {
        logger.info("[PengajuanSetorAction.searchDataSessionProsesPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionProsesPpn] stop process >>>");
        return (List<ProsesPpnKd>) session.getAttribute("listOfResultHasilProsesPpnKd");
    }

    public List<ProsesPpnKd> searchDataSessionProsesPpnB2() {
        logger.info("[PengajuanSetorAction.searchDataSessionProsesPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionProsesPpn] stop process >>>");
        return (List<ProsesPpnKd>) session.getAttribute("listOfResultHasilProsesPpnKdB2");
    }

    public List<ProsesPpnKd> searchDataSessionProsesPpnB3() {
        logger.info("[PengajuanSetorAction.searchDataSessionProsesPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionProsesPpn] stop process >>>");
        return (List<ProsesPpnKd>) session.getAttribute("listOfResultHasilProsesPpnKdB3");
    }

    public PerhitunganPpnKd searchDataSessionHasilProsesPpn () {
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpn] stop process >>>");
        return (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKd");
    }

    public PerhitunganKembaliPpn searchDataSessionPerhitunganKembali () {
        logger.info("[PengajuanSetorAction.searchDataSessionPerhitunganKembali] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPerhitunganKembali] stop process >>>");
        return (PerhitunganKembaliPpn) session.getAttribute("perhitunganKembaliPpn");
    }

    public PerhitunganPpnKd searchDataSessionHasilProsesPpnB2 () {
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpnB2] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpnB2] stop process >>>");
        return (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKdB2");
    }

    public PerhitunganPpnKd searchDataSessionHasilProsesPpnB3 () {
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpnB3] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpnB3] stop process >>>");
        return (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKdB3");
    }

    public PerhitunganPpnKd searchDataSessionHasilProsesPpnPenyerahanBarangDanJasa () {
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpnPenyerahanBarangDanJasa] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionHasilProsesPpnPenyerahanBarangDanJasa] stop process >>>");
        return (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKdPenyerahanBarangDanJasa");
    }

    public PengajuanSetor editSessionPpnMasukan(String transaksiId,String data) {
        logger.info("[PengajuanSetorAction.editSessionPpnMasukan] start process >>>");
        PengajuanSetor addPengajuanSetor = new PengajuanSetor();

        HttpSession session = ServletActionContext.getRequest().getSession();

        List<PengajuanSetorDetail> pengajuanSetorPpnMasukanList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukan");
        List<PengajuanSetorDetail> pengajuanSetorPpnMasukanB2List = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukanB2");
        List<PengajuanSetorDetail> pengajuanSetorPpnMasukanB3List = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukanB3");
        List<PengajuanSetorDetail> pengajuanSetorPpnKeluaranList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");
        PengajuanSetor resultPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        if (pengajuanSetorPpnMasukanB2List==null){
            pengajuanSetorPpnMasukanB2List =new ArrayList<>();
        }
        if (pengajuanSetorPpnMasukanB3List==null){
            pengajuanSetorPpnMasukanB3List =new ArrayList<>();
        }

        addPengajuanSetor.setJumlahPpnMasukanB2(BigDecimal.ZERO);
        addPengajuanSetor.setJumlahPpnMasukanB3(BigDecimal.ZERO);
        addPengajuanSetor.setJumlahPpnKeluaran(BigDecimal.ZERO);
        addPengajuanSetor.setJumlahSeluruhnya(BigDecimal.ZERO);

        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorPpnMasukanList){
            if (pengajuanSetorDetail.getTransaksiId().equalsIgnoreCase(transaksiId)){
                boolean sudahUbah=false;
                int length = 0;
                pengajuanSetorDetail.setDibayar(data);

                //untuk B2
                for (PengajuanSetorDetail pengajuanSetorPPnMasukanB2 : pengajuanSetorPpnMasukanB2List){
                    if (pengajuanSetorPPnMasukanB2.getTransaksiId().equalsIgnoreCase(transaksiId)){
                        if ("".equalsIgnoreCase(data)){
                            pengajuanSetorPpnMasukanB2List.remove(length);
                            sudahUbah=true;
                            break;
                        }else if("b2".equalsIgnoreCase(data)){
                            pengajuanSetorDetail.setTipe("PPN Masukan B2");
                            sudahUbah=true;
                            break;
                        }
                        else if("b3".equalsIgnoreCase(data)){
                            pengajuanSetorPpnMasukanB2List.remove(length);
                            break;
                        }
                    }
                    length++;
                }

                length = 0;
                //untuk B3
                for (PengajuanSetorDetail pengajuanSetorPPnMasukanB3 : pengajuanSetorPpnMasukanB3List){
                    if (pengajuanSetorPPnMasukanB3.getTransaksiId().equalsIgnoreCase(transaksiId)){
                        if ("".equalsIgnoreCase(data)){
                            pengajuanSetorPpnMasukanB3List.remove(length);
                            sudahUbah=true;
                            break;
                        }else if("b3".equalsIgnoreCase(data)){
                            pengajuanSetorDetail.setTipe("PPN Masukan B3");
                            sudahUbah=true;
                            break;
                        }
                        else if("b2".equalsIgnoreCase(data)){
                            pengajuanSetorPpnMasukanB3List.remove(length);
                            break;
                        }
                    }
                    length++;
                }

                //jika data baru
                if (!sudahUbah){
                    if (data.equalsIgnoreCase("b2")){
                        pengajuanSetorDetail.setTipe("PPN Masukan B2");
                        pengajuanSetorPpnMasukanB2List.add(pengajuanSetorDetail);
                        break;
                    }else if (data.equalsIgnoreCase("b3")){
                        pengajuanSetorDetail.setTipe("PPN Masukan B3");
                        pengajuanSetorPpnMasukanB3List.add(pengajuanSetorDetail);
                        break;
                    }
                }
            }
        }

        //melakukan total ppn keluaran
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorPpnKeluaranList){
            addPengajuanSetor.setJumlahPpnKeluaran(addPengajuanSetor.getJumlahPpnKeluaran().add(pengajuanSetorDetail.getJumlah()));
        }

        //melakukan total ppn masukan B2
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorPpnMasukanB2List){
            addPengajuanSetor.setJumlahPpnMasukanB2(addPengajuanSetor.getJumlahPpnMasukanB2().add(pengajuanSetorDetail.getJumlah()));
        }

        //melakukan total masukan B3
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorPpnMasukanB3List){
            addPengajuanSetor.setJumlahPpnMasukanB3(addPengajuanSetor.getJumlahPpnMasukanB3().add(pengajuanSetorDetail.getJumlah()));
        }

        //Total Seluruhnya
        addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahPpnMasukanB2().add(addPengajuanSetor.getJumlahPpnMasukanB3().add(addPengajuanSetor.getJumlahPpnKeluaran())));

        //set string
        addPengajuanSetor.setStJumlahPpnKeluaran(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPpnKeluaran(),"###,###"));
        addPengajuanSetor.setStJumlahPpnMasukanB2(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPpnMasukanB2(),"###,###"));
        addPengajuanSetor.setStJumlahPpnMasukanB3(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPpnMasukanB3(),"###,###"));
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));

        resultPengajuanSetor.setStJumlahPpnKeluaran(addPengajuanSetor.getStJumlahPpnKeluaran());
        resultPengajuanSetor.setStJumlahPpnMasukanB2(addPengajuanSetor.getStJumlahPpnMasukanB2());
        resultPengajuanSetor.setStJumlahPpnMasukanB3(addPengajuanSetor.getStJumlahPpnMasukanB3());
        resultPengajuanSetor.setStJumlahPpnKeluaran(addPengajuanSetor.getStJumlahPpnKeluaran());
        resultPengajuanSetor.setStJumlahSeluruhnya(addPengajuanSetor.getStJumlahSeluruhnya());
        resultPengajuanSetor.setJumlahPpnKeluaran(addPengajuanSetor.getJumlahPpnKeluaran());
        resultPengajuanSetor.setJumlahPpnMasukanB2(addPengajuanSetor.getJumlahPpnMasukanB2());
        resultPengajuanSetor.setJumlahPpnMasukanB3(addPengajuanSetor.getJumlahPpnMasukanB3());
        resultPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya());

        session.setAttribute("liresultAddPengajuanSetorPpnstOfResultPencarianDataMasukan", pengajuanSetorPpnMasukanList);
        session.setAttribute("listOfResultPencarianDataMasukanB2", pengajuanSetorPpnMasukanB2List);
        session.setAttribute("listOfResultPencarianDataMasukanB3", pengajuanSetorPpnMasukanB3List);
        session.setAttribute("listOfResultPengajuanSetor", resultPengajuanSetor);

        logger.info("[PengajuanSetorAction.editSessionPpnMasukan] stop process >>>");
        return addPengajuanSetor;
    }

    public PengajuanSetor editSessionPpnKeluaran(String id,boolean check) {
        logger.info("[PengajuanSetorAction.editSessionPpnKeluaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        List<PengajuanSetorDetail> pengajuanSetorDetailList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
            if (id.equalsIgnoreCase(pengajuanSetorDetail.getTransaksiId())){
                if (check){
                    pengajuanSetorDetail.setDibayar("Y");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().add(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPpnKeluaran(addPengajuanSetor.getJumlahPpnKeluaran().add(pengajuanSetorDetail.getJumlah()));
                }else{
                    pengajuanSetorDetail.setDibayar("N");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().subtract(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPpnKeluaran(addPengajuanSetor.getJumlahPpnKeluaran().subtract(pengajuanSetorDetail.getJumlah()));
                }
            }
        }
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));
        addPengajuanSetor.setStJumlahPpnKeluaran(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPpnKeluaran(),"###,###"));

        logger.info("[PengajuanSetorAction.editSessionPpnKeluaran] stop process >>>");

        session.setAttribute("listOfResultPengajuanSetor", addPengajuanSetor);
        session.setAttribute("listOfResultPencarianDataKeluaran", pengajuanSetorDetailList);

        return addPengajuanSetor;
    }

    public String saveAddPengajuanSetorPpn(){
        logger.info("[PengajuanSetorAction.saveAddPengajuanSetorPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor pengajuanSetor = getPengajuanSetor();
        List<PengajuanSetorDetail> pengajuanSetorDetailListMasukanB2 = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukanB2");
        List<PengajuanSetorDetail> pengajuanSetorDetailListKeluaran = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");
        List<PengajuanSetorDetail> pengajuanSetorDetailListMasukanB3 = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukanB3");

        String username = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pengajuanSetor.setAction("C");
        pengajuanSetor.setFlag("Y");
        pengajuanSetor.setCreatedWho(username);
        pengajuanSetor.setLastUpdateWho(username);
        pengajuanSetor.setLastUpdate(time);
        pengajuanSetor.setCreatedDate(time);
        pengajuanSetor.setRegisteredDate(CommonUtil.convertStringToDate(pengajuanSetor.getStRegisteredDate()));

        try {
            pengajuanSetorBoProxy.saveAddPengajuanSetorPpn(pengajuanSetor,pengajuanSetorDetailListMasukanB2,pengajuanSetorDetailListKeluaran,pengajuanSetorDetailListMasukanB3);
        } catch (GeneralBOException e) {
            logger.error("[PengajuanSetorAction.saveAddPengajuanSetorPpn] Error when save : ", e);
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PengajuanSetorAction.saveAddPengajuanSetorPpn] stop process >>>");
        return "success_save_pengajuan_setor_ppn";
    }

    public String saveAddTmpPengajuanSetorPpn(){
        logger.info("[PengajuanSetorAction.saveAddTmpPengajuanSetorPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor pengajuanSetor = getPengajuanSetor();
        List<PengajuanSetorDetail> pengajuanSetorDetailListKeluaran = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");

        int length=0;
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailListKeluaran){
            if (!"Y".equalsIgnoreCase(pengajuanSetorDetail.getDibayar())){
                pengajuanSetorDetailListKeluaran.remove(length);
            }
            length++;
        }

        session.setAttribute("listOfResultPencarianDataKeluaran",pengajuanSetorDetailListKeluaran);
        session.setAttribute("listOfResultPengajuanSetor",pengajuanSetor);


        if (pengajuanSetor.getJumlahPpnMasukanB2().compareTo(pengajuanSetor.getJumlahPpnKeluaran())>0){
            String status ="ERROR : PPN Masukan B2 tidak boleh melebihi PPN Keluaran";
            logger.error("[PengajuanSetorAction.saveAddPengajuanSetorPpn] Error when save : "+status);
            throw new GeneralBOException(status);
        }
        logger.info("[PengajuanSetorAction.saveAddTmpPengajuanSetorPpn] stop process >>>");

        return "success_save_tmp_pengajuan_setor_ppn";
    }

    public String prosesPPnKanpus(){
        logger.info("[PengajuanSetorAction.prosesPPnKanpus] start process >>>");

        List<ProsesPpnKd> hasilProsesPerUnit = new ArrayList<>();
        List<ProsesPpnKd> hasilProsesPerUnitB2 = new ArrayList<>();
        List<ProsesPpnKd> hasilProsesPerUnitB3 = new ArrayList<>();

        PengajuanSetor search = getPengajuanSetor();

        PerhitunganPpnKd perhitunganPpnKd = new PerhitunganPpnKd();
        PerhitunganPpnKd perhitunganPpnKdB2 = new PerhitunganPpnKd();
        PerhitunganPpnKd perhitunganPpnKdB3 = new PerhitunganPpnKd();

        //set tahun dan bulan
        perhitunganPpnKd.setTahun(search.getTahun());
        perhitunganPpnKd.setBulan(search.getBulan());
        perhitunganPpnKdB2.setTahun(search.getTahun());
        perhitunganPpnKdB2.setBulan(search.getBulan());
        perhitunganPpnKdB3.setTahun(search.getTahun());
        perhitunganPpnKdB3.setBulan(search.getBulan());

        PerhitunganPpnKd perhitunganPpnKdPenyerahanBarangDanJasa = new PerhitunganPpnKd();

        HttpSession session = ServletActionContext.getRequest().getSession();

        // validasi jika bulan sudah di proses
        List<ItAkunPerhitunganPpnKdEntity> perhitunganPpnKdEntityList = pengajuanSetorBoProxy.getListUntukValidasi(perhitunganPpnKd);

        if (perhitunganPpnKdEntityList.size()>0){
            String status = "ERROR : Periode ini sudah di proses .";
            logger.error(status);
            throw new GeneralBOException(status);
        }

        try {
            hasilProsesPerUnit= pengajuanSetorBoProxy.prosesPPnKanpus(search);
            hasilProsesPerUnitB2= pengajuanSetorBoProxy.prosesPPnKanpusB2(search);
            hasilProsesPerUnitB3= pengajuanSetorBoProxy.prosesPPnKanpusB3(search);
        } catch (GeneralBOException e) {
            logger.error("[PengajuanSetorAction.prosesPPnKanpus] Error when save : ", e);
            throw new GeneralBOException(e.getMessage());
        }

        for (ProsesPpnKd prosesPpnKd : hasilProsesPerUnit){
            prosesPpnKd.setStKeluaranUnit(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranUnit(),"###,###"));
            prosesPpnKd.setStMasukanUnit(CommonUtil.numbericFormat(prosesPpnKd.getMasukanUnit(),"###,###"));
            prosesPpnKd.setStKeluaranKoreksi(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranKoreksi(),"###,###"));
            prosesPpnKd.setStMasukanKoreksi(CommonUtil.numbericFormat(prosesPpnKd.getMasukanKoreksi(),"###,###"));
            prosesPpnKd.setStKeluaranDiambilKp(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranDiambilKp(),"###,###"));
            prosesPpnKd.setStMasukanDiambilKp(CommonUtil.numbericFormat(prosesPpnKd.getMasukanDiambilKp(),"###,###"));

            //menghitung summary
            if ("konsol".equalsIgnoreCase(prosesPpnKd.getBranchId())){
                perhitunganPpnKd.setPpnMasukan(prosesPpnKd.getMasukanDiambilKp());
                perhitunganPpnKd.setPpnKeluaran(prosesPpnKd.getKeluaranDiambilKp());

                //Penyerahan Barang dan jasa
                //terutang PPN
                perhitunganPpnKd.setDipungutSendiri(prosesPpnKd.getKeluaranUnit().divide((BigDecimal.TEN).divide(BigDecimal.valueOf(100))));
                perhitunganPpnKd.setJumlahTerutangPpn(perhitunganPpnKd.getPpnEkspor()
                        .add(perhitunganPpnKd.getDipungutSendiri()).add(perhitunganPpnKd.getDipungutOlehPemungut())
                        .add(perhitunganPpnKd.getTidakDipungut().add(perhitunganPpnKd.getDibebaskan())));

            }
        }

        //menghitung summary
        perhitunganPpnKd.setTotalPpnMasukan(perhitunganPpnKd.getLbBulanYll().add(perhitunganPpnKd.getPpnMasukan()));
        perhitunganPpnKd.setKurangBayar(perhitunganPpnKd.getTotalPpnMasukan().subtract(perhitunganPpnKd.getPpnKeluaran()));
        perhitunganPpnKd.setPerhitunganKembali(BigDecimal.ZERO);
        perhitunganPpnKd.setTotalKurangBayar(perhitunganPpnKd.getKurangBayar().subtract(perhitunganPpnKd.getPerhitunganKembali()));

        if (perhitunganPpnKd.getTotalKurangBayar().compareTo(BigDecimal.ZERO)<0){
            perhitunganPpnKd.setStatusB2("kurang_bayar");
            perhitunganPpnKd.setTotalKurangBayar(perhitunganPpnKd.getTotalKurangBayar().abs());
        }else{
            perhitunganPpnKd.setStatusB2("lebih_bayar");
        }

        //tidak terhutang
        perhitunganPpnKd.setJasaRs(pengajuanSetorBoProxy.getJasaRs(search));
        perhitunganPpnKd.setObatRawatInap(pengajuanSetorBoProxy.getObatrawatInap(search));
        perhitunganPpnKd.setJumlahTidakTerutang(perhitunganPpnKd.getJasaRs().add(perhitunganPpnKd.getObatRawatInap()));

        //jumlah A + B
        perhitunganPpnKd.setPenyerahanBarangDanJasa(perhitunganPpnKd.getJumlahTerutangPpn().add(perhitunganPpnKd.getJumlahTidakTerutang()));

        //convert ke string
        perhitunganPpnKd.setStPpnMasukan(CommonUtil.numbericFormat(perhitunganPpnKd.getPpnMasukan(),"###,###"));
        perhitunganPpnKd.setStPpnKeluaran(CommonUtil.numbericFormat(perhitunganPpnKd.getPpnKeluaran(),"###,###"));
        perhitunganPpnKd.setStKurangBayar(CommonUtil.numbericFormat(perhitunganPpnKd.getKurangBayar(),"###,###"));
        perhitunganPpnKd.setStPerhitunganKembali(CommonUtil.numbericFormat(perhitunganPpnKd.getPerhitunganKembali(),"###,###"));
        perhitunganPpnKd.setStTotalKurangBayar(CommonUtil.numbericFormat(perhitunganPpnKd.getTotalKurangBayar(),"###,###"));
        perhitunganPpnKd.setStLbBulanYll(CommonUtil.numbericFormat(perhitunganPpnKd.getLbBulanYll(),"###,###"));
        perhitunganPpnKd.setStTotalPpnMasukan(CommonUtil.numbericFormat(perhitunganPpnKd.getTotalPpnMasukan(),"###,###"));

        //PERHITUNGAN PPN KD PENYERAHAN BARANG DAN JASA
        perhitunganPpnKdPenyerahanBarangDanJasa.setPpnEkspor(perhitunganPpnKd.getPpnEkspor());
        perhitunganPpnKdPenyerahanBarangDanJasa.setDipungutSendiri(perhitunganPpnKd.getDipungutSendiri());
        perhitunganPpnKdPenyerahanBarangDanJasa.setDipungutOlehPemungut(perhitunganPpnKd.getDipungutOlehPemungut());
        perhitunganPpnKdPenyerahanBarangDanJasa.setTidakDipungut(perhitunganPpnKd.getTidakDipungut());
        perhitunganPpnKdPenyerahanBarangDanJasa.setDibebaskan(perhitunganPpnKd.getDibebaskan());
        perhitunganPpnKdPenyerahanBarangDanJasa.setJumlahTerutangPpn(perhitunganPpnKd.getJumlahTerutangPpn());
        perhitunganPpnKdPenyerahanBarangDanJasa.setJasaRs(perhitunganPpnKd.getJasaRs());
        perhitunganPpnKdPenyerahanBarangDanJasa.setObatRawatInap(perhitunganPpnKd.getObatRawatInap());
        perhitunganPpnKdPenyerahanBarangDanJasa.setJumlahTidakTerutang(perhitunganPpnKd.getJumlahTidakTerutang());
        perhitunganPpnKdPenyerahanBarangDanJasa.setPenyerahanBarangDanJasa(perhitunganPpnKd.getPenyerahanBarangDanJasa());

        perhitunganPpnKdPenyerahanBarangDanJasa.setStPpnEkspor(CommonUtil.numbericFormat(perhitunganPpnKd.getPpnEkspor(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStDipungutSendiri(CommonUtil.numbericFormat(perhitunganPpnKd.getDipungutSendiri(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStDipungutOlehPemungut(CommonUtil.numbericFormat(perhitunganPpnKd.getDipungutOlehPemungut(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStTidakDipungut(CommonUtil.numbericFormat(perhitunganPpnKd.getTidakDipungut(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStDibebaskan(CommonUtil.numbericFormat(perhitunganPpnKd.getDibebaskan(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStJumlahTerutangPpn(CommonUtil.numbericFormat(perhitunganPpnKd.getJumlahTerutangPpn(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStJasaRs(CommonUtil.numbericFormat(perhitunganPpnKd.getJasaRs(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStObatRawatInap(CommonUtil.numbericFormat(perhitunganPpnKd.getObatRawatInap(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStJumlahTidakTerutang(CommonUtil.numbericFormat(perhitunganPpnKd.getJumlahTidakTerutang(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStPenyerahanBarangDanJasa(CommonUtil.numbericFormat(perhitunganPpnKd.getPenyerahanBarangDanJasa(),"###,###"));

        //cek pajak B2
        for (ProsesPpnKd prosesPpnKd : hasilProsesPerUnitB2){
            prosesPpnKd.setStKeluaranUnit(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranUnit(),"###,###"));
            prosesPpnKd.setStMasukanUnit(CommonUtil.numbericFormat(prosesPpnKd.getMasukanUnit(),"###,###"));
            prosesPpnKd.setStKeluaranKoreksi(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranKoreksi(),"###,###"));
            prosesPpnKd.setStMasukanKoreksi(CommonUtil.numbericFormat(prosesPpnKd.getMasukanKoreksi(),"###,###"));
            prosesPpnKd.setStKeluaranDiambilKp(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranDiambilKp(),"###,###"));
            prosesPpnKd.setStMasukanDiambilKp(CommonUtil.numbericFormat(prosesPpnKd.getMasukanDiambilKp(),"###,###"));

            //menghitung summary
            if ("konsol".equalsIgnoreCase(prosesPpnKd.getBranchId())){
                perhitunganPpnKdB2.setPpnMasukan(prosesPpnKd.getMasukanDiambilKp());
                perhitunganPpnKdB2.setPpnKeluaran(prosesPpnKd.getKeluaranDiambilKp());

                //Penyerahan Barang dan jasa
                //terutang PPN
                perhitunganPpnKdB2.setDipungutSendiri(prosesPpnKd.getKeluaranUnit().divide((BigDecimal.TEN).divide(BigDecimal.valueOf(100))));
                perhitunganPpnKdB2.setJumlahTerutangPpn(perhitunganPpnKdB2.getPpnEkspor()
                        .add(perhitunganPpnKdB2.getDipungutSendiri()).add(perhitunganPpnKdB2.getDipungutOlehPemungut())
                        .add(perhitunganPpnKdB2.getTidakDipungut().add(perhitunganPpnKdB2.getDibebaskan())));

            }

            //menghitung summary
            perhitunganPpnKdB2.setTotalPpnMasukan(perhitunganPpnKdB2.getLbBulanYll().add(perhitunganPpnKdB2.getPpnMasukan()));
            perhitunganPpnKdB2.setKurangBayar(perhitunganPpnKdB2.getTotalPpnMasukan().subtract(perhitunganPpnKdB2.getPpnKeluaran()));
            perhitunganPpnKdB2.setPerhitunganKembali(pengajuanSetorBoProxy.perhitunganKembaliPpn(search));
            perhitunganPpnKdB2.setTotalKurangBayar(perhitunganPpnKdB2.getKurangBayar().subtract(perhitunganPpnKdB2.getPerhitunganKembali()));

            if (perhitunganPpnKdB2.getTotalKurangBayar().compareTo(BigDecimal.ZERO)<0){
                perhitunganPpnKdB2.setStatusB2("kurang_bayar");
                perhitunganPpnKdB2.setTotalKurangBayar(perhitunganPpnKdB2.getTotalKurangBayar().abs());
            }else{
                perhitunganPpnKdB2.setStatusB2("lebih_bayar");
            }

            //tidak terhutang
            perhitunganPpnKdB2.setJumlahTidakTerutang(perhitunganPpnKdB2.getJasaRs().add(perhitunganPpnKdB2.getObatRawatInap()));

            //jumlah A + B
            perhitunganPpnKdB2.setPenyerahanBarangDanJasa(perhitunganPpnKdB2.getJumlahTerutangPpn().add(perhitunganPpnKdB2.getJumlahTidakTerutang()));

        }

        //convert ke string
        perhitunganPpnKdB2.setStPpnMasukan(CommonUtil.numbericFormat(perhitunganPpnKdB2.getPpnMasukan(),"###,###"));
        perhitunganPpnKdB2.setStPpnKeluaran(CommonUtil.numbericFormat(perhitunganPpnKdB2.getPpnKeluaran(),"###,###"));
        perhitunganPpnKdB2.setStKurangBayar(CommonUtil.numbericFormat(perhitunganPpnKdB2.getKurangBayar(),"###,###"));
        perhitunganPpnKdB2.setStPerhitunganKembali(CommonUtil.numbericFormat(perhitunganPpnKdB2.getPerhitunganKembali(),"###,###"));
        perhitunganPpnKdB2.setStTotalKurangBayar(CommonUtil.numbericFormat(perhitunganPpnKdB2.getTotalKurangBayar(),"###,###"));
        perhitunganPpnKdB2.setStLbBulanYll(CommonUtil.numbericFormat(perhitunganPpnKdB2.getLbBulanYll(),"###,###"));
        perhitunganPpnKdB2.setStTotalPpnMasukan(CommonUtil.numbericFormat(perhitunganPpnKdB2.getTotalPpnMasukan(),"###,###"));


        //cek pajak B3
        for (ProsesPpnKd prosesPpnKd : hasilProsesPerUnitB3){
            prosesPpnKd.setStKeluaranUnit(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranUnit(),"###,###"));
            prosesPpnKd.setStMasukanUnit(CommonUtil.numbericFormat(prosesPpnKd.getMasukanUnit(),"###,###"));
            prosesPpnKd.setStKeluaranKoreksi(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranKoreksi(),"###,###"));
            prosesPpnKd.setStMasukanKoreksi(CommonUtil.numbericFormat(prosesPpnKd.getMasukanKoreksi(),"###,###"));
            prosesPpnKd.setStKeluaranDiambilKp(CommonUtil.numbericFormat(prosesPpnKd.getKeluaranDiambilKp(),"###,###"));
            prosesPpnKd.setStMasukanDiambilKp(CommonUtil.numbericFormat(prosesPpnKd.getMasukanDiambilKp(),"###,###"));

        }
        //convert ke string
        perhitunganPpnKdB3.setStPpnMasukan(CommonUtil.numbericFormat(perhitunganPpnKdB3.getPpnMasukan(),"###,###"));
        perhitunganPpnKdB3.setStPpnKeluaran(CommonUtil.numbericFormat(perhitunganPpnKdB3.getPpnKeluaran(),"###,###"));
        perhitunganPpnKdB3.setStKurangBayar(CommonUtil.numbericFormat(perhitunganPpnKdB3.getKurangBayar(),"###,###"));
        perhitunganPpnKdB3.setStPerhitunganKembali(CommonUtil.numbericFormat(perhitunganPpnKdB3.getPerhitunganKembali(),"###,###"));
        perhitunganPpnKdB3.setStTotalKurangBayar(CommonUtil.numbericFormat(perhitunganPpnKdB3.getTotalKurangBayar(),"###,###"));
        perhitunganPpnKdB3.setStLbBulanYll(CommonUtil.numbericFormat(perhitunganPpnKdB3.getLbBulanYll(),"###,###"));
        perhitunganPpnKdB3.setStTotalPpnMasukan(CommonUtil.numbericFormat(perhitunganPpnKdB3.getTotalPpnMasukan(),"###,###"));

        session.setAttribute("listOfResultHasilProsesPpnKd",hasilProsesPerUnit);
        session.setAttribute("listOfResultHasilProsesPpnKdB2",hasilProsesPerUnitB2);
        session.setAttribute("listOfResultHasilProsesPpnKdB3",hasilProsesPerUnitB3);
        session.setAttribute("resultPerhitunganPpnKd",perhitunganPpnKd);
        session.setAttribute("resultPerhitunganPpnKdB2",perhitunganPpnKdB2);
        session.setAttribute("resultPerhitunganPpnKdB3",perhitunganPpnKdB3);
        session.setAttribute("resultPerhitunganPpnKdPenyerahanBarangDanJasa",perhitunganPpnKdPenyerahanBarangDanJasa);

        logger.info("[PengajuanSetorAction.prosesPPnKanpus] stop process >>>");
        return "success_proses_ppn_kanpus";
    }

    public String saveProsesPpnKanpus(){
        logger.info("[PengajuanSetorAction.saveProsesPpnKanpus] start process >>>");
        PerhitunganPpnKd perhitunganPpnKd = getPerhitunganPpnKd();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ProsesPpnKd> prosesPpnKdListNormal = (List<ProsesPpnKd>) session.getAttribute("listOfResultHasilProsesPpnKd");
        List<ProsesPpnKd> prosesPpnKdListB2 = (List<ProsesPpnKd>) session.getAttribute("listOfResultHasilProsesPpnKdB2");
        List<ProsesPpnKd> prosesPpnKdListB3 = (List<ProsesPpnKd>) session.getAttribute("listOfResultHasilProsesPpnKdB3");
        PerhitunganPpnKd perhitunganPpnKdListNormal = (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKd");
        PerhitunganPpnKd perhitunganPpnKdListB2 = (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKdB2");
        PerhitunganPpnKd perhitunganPpnKdListB3 = (PerhitunganPpnKd) session.getAttribute("resultPerhitunganPpnKdB3");
        PerhitunganKembaliPpn perhitunganKembaliPpn = (PerhitunganKembaliPpn) session.getAttribute("perhitunganKembaliPpn");


        String username = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        perhitunganPpnKd.setAction("C");
        perhitunganPpnKd.setFlag("Y");
        perhitunganPpnKd.setCreatedWho(username);
        perhitunganPpnKd.setLastUpdateWho(username);
        perhitunganPpnKd.setLastUpdate(time);
        perhitunganPpnKd.setCreatedDate(time);
        perhitunganPpnKd.setCancelFlag("N");

        try {
            pengajuanSetorBoProxy.saveAddProsesPpnKd(perhitunganPpnKd,prosesPpnKdListNormal,prosesPpnKdListB2,prosesPpnKdListB3,perhitunganPpnKdListNormal,perhitunganPpnKdListB2,perhitunganPpnKdListB3,perhitunganKembaliPpn);
        } catch (GeneralBOException e) {
            logger.error("[PengajuanSetorAction.saveProsesPpnKanpus] Error when save : ", e);
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PengajuanSetorAction.saveProsesPpnKanpus] stop process >>>");
        return "success_save_proses_ppn_Kanpus";
    }

    public String searchProsesPpnKd() {
        logger.info("[PengajuanSetorAction.searchProsesPpnKd] start process >>>");
        PerhitunganPpnKd search = getPerhitunganPpnKd();
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResult");
            List<PerhitunganPpnKd> listOfsearch= pengajuanSetorBoProxy.getSearchHomeProsesPpnKd(search);
            session.setAttribute("listOfResult", listOfsearch);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanSetorBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        perhitunganPpnKd=search;

        String branchId = CommonUtil.userBranchLogin();
        PengajuanSetor data = new PengajuanSetor();
        data.setBranchId(branchId);
        setPengajuanSetor(data);

        logger.info("[PengajuanSetorAction.searchProsesPpnKd] stop process >>>");
        return "success_search_proses_ppn";
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        logger.info("[PengajuanSetorAction.view] start process >>>");
        PerhitunganPpnKd search = new PerhitunganPpnKd();
        search.setBulan(getBulan());
        search.setTahun(getTahun());

        //Normal
        PerhitunganPpnKd perhitunganPpnKdNormal = pengajuanSetorBoProxy.getPerhitunganPpnKdList(search,"normal");
        List<ProsesPpnKd> prosesPpnKdListNormal = pengajuanSetorBoProxy.getProsesPpnKdList(perhitunganPpnKdNormal.getPerhitunganPpnKdId());

        //B2
        PerhitunganPpnKd perhitunganPpnKdB2 = pengajuanSetorBoProxy.getPerhitunganPpnKdList(search,"B2");
        List<ProsesPpnKd> prosesPpnKdListB2 = pengajuanSetorBoProxy.getProsesPpnKdList(perhitunganPpnKdB2.getPerhitunganPpnKdId());

        //B3
        PerhitunganPpnKd perhitunganPpnKdB3 = pengajuanSetorBoProxy.getPerhitunganPpnKdList(search,"B3");
        List<ProsesPpnKd> prosesPpnKdListB3 = pengajuanSetorBoProxy.getProsesPpnKdList(perhitunganPpnKdB3.getPerhitunganPpnKdId());

        //Perhitungan Kembali
        PerhitunganKembaliPpn perhitunganKembaliPpn = pengajuanSetorBoProxy.getPerhitunganKembali(search);

        //Perhitungan Penyerahan Barang Dan Jasa
        PerhitunganPpnKd perhitunganPpnKdPenyerahanBarangDanJasa = new PerhitunganPpnKd();
        perhitunganPpnKdPenyerahanBarangDanJasa.setPpnEkspor(perhitunganPpnKdNormal.getPpnEkspor());
        perhitunganPpnKdPenyerahanBarangDanJasa.setDipungutSendiri(perhitunganPpnKdNormal.getDipungutSendiri());
        perhitunganPpnKdPenyerahanBarangDanJasa.setDipungutOlehPemungut(perhitunganPpnKdNormal.getDipungutOlehPemungut());
        perhitunganPpnKdPenyerahanBarangDanJasa.setTidakDipungut(perhitunganPpnKdNormal.getTidakDipungut());
        perhitunganPpnKdPenyerahanBarangDanJasa.setDibebaskan(perhitunganPpnKdNormal.getDibebaskan());
        perhitunganPpnKdPenyerahanBarangDanJasa.setJumlahTerutangPpn(perhitunganPpnKdNormal.getJumlahTerutangPpn());
        perhitunganPpnKdPenyerahanBarangDanJasa.setJasaRs(perhitunganPpnKdNormal.getJasaRs());
        perhitunganPpnKdPenyerahanBarangDanJasa.setObatRawatInap(perhitunganPpnKdNormal.getObatRawatInap());
        perhitunganPpnKdPenyerahanBarangDanJasa.setJumlahTidakTerutang(perhitunganPpnKdNormal.getJumlahTidakTerutang());
        perhitunganPpnKdPenyerahanBarangDanJasa.setPenyerahanBarangDanJasa(perhitunganPpnKdNormal.getPenyerahanBarangDanJasa());

        perhitunganPpnKdPenyerahanBarangDanJasa.setStPpnEkspor(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getPpnEkspor(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStDipungutSendiri(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getDipungutSendiri(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStDipungutOlehPemungut(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getDipungutOlehPemungut(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStTidakDipungut(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getTidakDipungut(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStDibebaskan(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getDibebaskan(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStJumlahTerutangPpn(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getJumlahTerutangPpn(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStJasaRs(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getJasaRs(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStObatRawatInap(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getObatRawatInap(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStJumlahTidakTerutang(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getJumlahTidakTerutang(),"###,###"));
        perhitunganPpnKdPenyerahanBarangDanJasa.setStPenyerahanBarangDanJasa(CommonUtil.numbericFormat(perhitunganPpnKdNormal.getPenyerahanBarangDanJasa(),"###,###"));

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultHasilProsesPpnKd",prosesPpnKdListNormal);
        session.setAttribute("listOfResultHasilProsesPpnKdB2",prosesPpnKdListB2);
        session.setAttribute("listOfResultHasilProsesPpnKdB3",prosesPpnKdListB3);
        session.setAttribute("resultPerhitunganPpnKd",perhitunganPpnKdNormal);
        session.setAttribute("resultPerhitunganPpnKdB2",perhitunganPpnKdB2);
        session.setAttribute("resultPerhitunganPpnKdB3",perhitunganPpnKdB3);
        session.setAttribute("perhitunganKembaliPpn",perhitunganKembaliPpn);
        session.setAttribute("resultPerhitunganPpnKdPenyerahanBarangDanJasa",perhitunganPpnKdPenyerahanBarangDanJasa);


        logger.info("[PengajuanSetorAction.view] stop process >>>");

        return "view_proses_ppn_Kanpus";
    }

    public String postingJurnalProsesPpn(String bulan, String tahun,String kas,String keterangan){
        logger.info("[PengajuanSetorAction.postingJurnalProsesPpn] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        try {
            //untuk posting jurnal Koreksi
            Map dataPostingJurnalKoreksi = pengajuanSetorBo.getBillingForPostingProsesPpnKoreksi(bulan,tahun);
            Jurnal jurnalPostingKoreksi = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PROSES_PPN_KD_KOREKSI,dataPostingJurnalKoreksi,CommonConstant.ID_KANPUS,keterangan,"Y");

            //Untuk Posting Jurnal kas Keluar
            Map dataPostingJurnalKasKeluar = pengajuanSetorBo.getBillingForPostingProsesPpnKasKeluar(bulan,tahun,kas);
            Jurnal jurnalPostingKasKeluar = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PROSES_PPN_KD_KAS_KELUAR,dataPostingJurnalKasKeluar,CommonConstant.ID_KANPUS,keterangan,"Y");

            PerhitunganPpnKd data = new PerhitunganPpnKd();
            data.setBulan(bulan);
            data.setTahun(tahun);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            pengajuanSetorBo.postingJurnalProsesPpn(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanSetorAction.postingJurnalProsesPpn");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanSetorAction.postingJurnalProsesPpn] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanSetorAction.postingJurnalProsesPpn] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanSetorAction.postingJurnalProsesPpn] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String cancelProsesPpn(String bulan,String tahun){
        logger.info("[PengajuanSetorAction.cancelProsesPpn] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
            PerhitunganPpnKd data = new PerhitunganPpnKd();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setCancelDate(updateTime);
            data.setCancelFlag("Y");
            data.setCancelWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            pengajuanSetorBo.cancelProsesPpn(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanSetorBoProxy.saveErrorMessage(e.getMessage(), "PengajuanSetorAction.cancelProsesPpn");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanSetorAction.cancelProsesPpn] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanSetorAction.cancelProsesPpn] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanSetorAction.cancelProsesPpn] end process <<<");

        return "Berhasil Membatalkan Proses Pembayaran PPN";
    }

    public String eksportCsvPph21(){
        DataOutputStream doStream = null; // declare a print stream object
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        PengajuanSetor pengajuanSetor = getPengajuanSetor();
        String bulan = "";
        String tahun = "";

        List<PengajuanSetor> pengajuanSetorList = pengajuanSetorBo.getByCriteria(pengajuanSetor);
        try {
            doStream = new DataOutputStream(new FileOutputStream("tarikan_pajak_pph21.csv"));

            Branch branch = branchBo.getBranchById(CommonConstant.ID_KANPUS,"Y");
            for (PengajuanSetor dataPengajuanSetor : pengajuanSetorList){
                bulan = dataPengajuanSetor.getBulanName();
                tahun = dataPengajuanSetor.getTahun();
            }

            String namaKantorPusat = branch.getBranchName();
            doStream.writeBytes(namaKantorPusat);
            doStream.writeBytes("\n");
            doStream.writeBytes("Nama Report : Tarikan PAJAK PPH21 Bulan "+bulan+" Tahun "+tahun);
            doStream.writeBytes("\n");
            doStream.writeBytes("\n");
            String[] headers = "Tipe,No. Sumber,ID,Nama,PPH21(RP)".split(",");

            for(int i=0; i < headers.length; i++)
            {
                if(i != headers.length-1)
                    doStream.writeBytes("\""+headers[i]+"\", ");
                else
                    doStream.writeBytes("\""+headers[i]+"\"");
            }
            doStream.writeBytes("\n");

            //list data
            List<PengajuanSetorDetail> dataPphList = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetor.getPengajuanSetorId(),"Payroll");
            dataPphList.addAll(pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetor.getPengajuanSetorId(),"Dokter KSO"));
            dataPphList.addAll(pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetor.getPengajuanSetorId(),"Pengajuan Biaya PPH21"));

            for (PengajuanSetorDetail a : dataPphList){
                doStream.writeBytes("\""+a.getTipe()+"\""+",");
                doStream.writeBytes("\""+a.getTransaksiId()+"\""+",");
                doStream.writeBytes("\""+a.getPersonId()+"\""+",");
                doStream.writeBytes("\""+a.getNama()+"\""+",");
                doStream.writeBytes("\""+a.getJumlah()+"\"");
                doStream.writeBytes("\n");
            }

            doStream.flush();
            doStream.close();
            setInputStream(new FileInputStream("tarikan_pajak_pph21.csv"));

        } // end try
        catch (Exception e) {
            e.printStackTrace();
        } // end catch
        return "export_hasil_csv";
    }

    public String eksportCsvPpn(){
        DataOutputStream doStream = null; // declare a print stream object
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanSetorBo pengajuanSetorBo = (PengajuanSetorBo) ctx.getBean("pengajuanSetorBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        PengajuanSetor pengajuanSetor = getPengajuanSetor();
        String bulan = "";
        String tahun = "";

        List<PengajuanSetor> pengajuanSetorList = pengajuanSetorBo.getByCriteria(pengajuanSetor);
        try {
            doStream = new DataOutputStream(new FileOutputStream("tarikan_pajak_ppn.csv"));

            Branch branch = branchBo.getBranchById(CommonConstant.ID_KANPUS,"Y");
            for (PengajuanSetor dataPengajuanSetor : pengajuanSetorList){
                bulan = dataPengajuanSetor.getBulanName();
                tahun = dataPengajuanSetor.getTahun();
            }

            String namaKantorPusat = branch.getBranchName();
            doStream.writeBytes(namaKantorPusat);
            doStream.writeBytes("\n");
            doStream.writeBytes("Nama Report : Tarikan PAJAK PPN Bulan "+bulan+" Tahun "+tahun);
            doStream.writeBytes("\n");
            doStream.writeBytes("\n");
            String[] headers = "Tipe,No. Sumber,ID,PPN(RP)".split(",");

            for(int i=0; i < headers.length; i++)
            {
                if(i != headers.length-1)
                    doStream.writeBytes("\""+headers[i]+"\", ");
                else
                    doStream.writeBytes("\""+headers[i]+"\"");
            }
            doStream.writeBytes("\n");

            //list data
            List<PengajuanSetorDetail> dataPphList = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetor.getPengajuanSetorId(),"PPN Masukan B2");
            dataPphList.addAll(pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetor.getPengajuanSetorId(),"PPN Keluaran"));

            for (PengajuanSetorDetail a : dataPphList){
                doStream.writeBytes("\""+a.getTipe()+"\""+",");
                doStream.writeBytes("\""+a.getTransaksiId()+"\""+",");
                doStream.writeBytes("\""+a.getPersonId()+"\""+",");
                doStream.writeBytes("\""+a.getJumlah()+"\"");
                doStream.writeBytes("\n");
            }

            doStream.flush();
            doStream.close();
            setInputStream(new FileInputStream("tarikan_pajak_ppn.csv"));

        } // end try
        catch (Exception e) {
            e.printStackTrace();
        } // end catch
        return "export_hasil_csv_ppn";
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
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

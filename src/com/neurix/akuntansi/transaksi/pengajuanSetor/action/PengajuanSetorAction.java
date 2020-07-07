package com.neurix.akuntansi.transaksi.pengajuanSetor.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.PengajuanSetorBo;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
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
    private PengajuanSetorDetail pengajuanSetorDetail;
    private List<PengajuanSetor> listOfComboPengajuanSetor = new ArrayList<PengajuanSetor>();

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
            String noJurnal = billingSystemBo.createJurnal("65",dataPostingJurnal,pengajuanSetor.getBranchId(),pengajuanSetor.getKeterangan(),"Y");

            data.setPengajuanSetorId(pengajuanSetorId);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalId(userLogin);
            data.setNoJurnal(noJurnal);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

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
        addPengajuanSetor.setBulanAsli(addPengajuanSetor.getBulan());
        addPengajuanSetor.setBulan(getSearchWhereBulan(addPengajuanSetor.getBulan()));

        List<PengajuanSetorDetail> pengajuanSetorDetailKeluaranList = pengajuanSetorBoProxy.listPPnKeluaran(addPengajuanSetor);
        List<PengajuanSetorDetail> pengajuanSetorDetailMasukanB2List = pengajuanSetorBoProxy.listPPnMasukan(addPengajuanSetor);

//        melakukan total keluaran
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailKeluaranList){
            resultPengajuanSetor.setJumlahPpnKeluaran(resultPengajuanSetor.getJumlahPpnKeluaran().add(pengajuanSetorDetail.getJumlah()));
        }

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
        return null;
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

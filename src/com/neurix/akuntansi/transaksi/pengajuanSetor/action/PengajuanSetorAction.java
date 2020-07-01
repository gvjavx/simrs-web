package com.neurix.akuntansi.transaksi.pengajuanSetor.action;

import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.PengajuanSetorBo;
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
        return "search_add_pengajuan_setor_ppn";
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
        addPengajuanSetor.setPeriode(addPengajuanSetor.getBulan()+"/"+addPengajuanSetor.getTahun());
        addPengajuanSetor.setBulanAsli(addPengajuanSetor.getBulan());
        addPengajuanSetor.setBulan(getSearchWhereBulan(addPengajuanSetor.getBulan()));

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
        List<PengajuanSetorDetail> listDetailMasukan = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailKeluaran = new ArrayList<>();
        List<PengajuanSetorDetail> listDetailAset = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(pengajuanSetorId, itemFlag);
            if (modalPopUp!=null){
                listDetailPayroll = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetorId,"Payroll");
                listDetailKso = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetorId,"Dokter KSO");
                listDetailPengajuan = pengajuanSetorBo.getDetailPengajuanSetorPPh21(pengajuanSetorId,"Pengajuan Biaya PPH21");

                listDetailMasukan = pengajuanSetorBo.getDetailPengajuanSetorPPn(pengajuanSetorId,"PPN Masukan");
                listDetailKeluaran = pengajuanSetorBo.getDetailPengajuanSetorPPn(pengajuanSetorId,"PPN Keluaran");
                listDetailAset = pengajuanSetorBo.getDetailPengajuanSetorPPn(pengajuanSetorId,"Pengajuan Biaya PPN");

                session.setAttribute("listOfResultPencarianDataPayroll",listDetailPayroll);
                session.setAttribute("listOfResultPencarianDataKso",listDetailKso);
                session.setAttribute("listOfResultPencarianDataPengajuan",listDetailPengajuan);
                session.setAttribute("listOfResultPencarianDataMasukan", listDetailMasukan);
                session.setAttribute("listOfResultPencarianDataKeluaran", listDetailKeluaran);
                session.setAttribute("listOfResultPencarianDataAset", listDetailAset);
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
            PengajuanSetor data = new PengajuanSetor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            //disini untuk posting jurnal untuk mendapat nojurnal
            String noJurnal = "";

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
        resultPengajuanSetor.setJumlahPpnMasukan(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahPpnKeluaran(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahPpnAset(BigDecimal.ZERO);
        resultPengajuanSetor.setJumlahSeluruhnya(BigDecimal.ZERO);
        resultPengajuanSetor.setTahun(addPengajuanSetor.getTahun());
        resultPengajuanSetor.setBulan(addPengajuanSetor.getBulan());
        HttpSession session = ServletActionContext.getRequest().getSession();
        addPengajuanSetor.setPeriode(addPengajuanSetor.getBulan()+"/"+addPengajuanSetor.getTahun());
        addPengajuanSetor.setBulanAsli(addPengajuanSetor.getBulan());
        addPengajuanSetor.setBulan(getSearchWhereBulan(addPengajuanSetor.getBulan()));

        List<PengajuanSetorDetail> pengajuanSetorDetailKeluaranList = pengajuanSetorBoProxy.listPPnKeluaran(addPengajuanSetor);
        List<PengajuanSetorDetail> pengajuanSetorDetailMasukanList = pengajuanSetorBoProxy.listPPnMasukan(addPengajuanSetor);
        List<PengajuanSetorDetail> pengajuanSetorDetailAsetList = pengajuanSetorBoProxy.listPPnPengajuan(addPengajuanSetor);

//        melakukan total keluaran
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailKeluaranList){
            resultPengajuanSetor.setJumlahPpnKeluaran(resultPengajuanSetor.getJumlahPpnKeluaran().add(pengajuanSetorDetail.getJumlah()));
        }

//        melakukan total masukan
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailMasukanList){
            resultPengajuanSetor.setJumlahPpnMasukan(resultPengajuanSetor.getJumlahPpnMasukan().add(pengajuanSetorDetail.getJumlah()));
        }

//        melakukan total aset
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailAsetList){
            resultPengajuanSetor.setJumlahPpnAset(resultPengajuanSetor.getJumlahPpnAset().add(pengajuanSetorDetail.getJumlah()));
        }


        //( PPN KELUARAN + PPN PENGAJUAN ) - PPN MASUKAN
        resultPengajuanSetor.setJumlahSeluruhnya((resultPengajuanSetor.getJumlahPpnKeluaran().add(resultPengajuanSetor.getJumlahPpnAset())).subtract(resultPengajuanSetor.getJumlahPpnMasukan()));

        //set string
        resultPengajuanSetor.setStJumlahPpnMasukan(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPpnKeluaran(),"###,###"));
        resultPengajuanSetor.setStJumlahPpnKeluaran(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPpnMasukan(),"###,###"));
        resultPengajuanSetor.setStJumlahPpnAset(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahPpnAset(),"###,###"));
        resultPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(resultPengajuanSetor.getJumlahSeluruhnya(),"###,###"));

        session.removeAttribute("listOfResultPengajuanSetor");
        session.removeAttribute("listOfResultPencarianDataMasukan");
        session.removeAttribute("listOfResultPencarianDataKeluaran");
        session.removeAttribute("listOfResultPencarianDataAset");
        session.setAttribute("listOfResultPencarianDataMasukan", pengajuanSetorDetailMasukanList);
        session.setAttribute("listOfResultPencarianDataKeluaran", pengajuanSetorDetailKeluaranList);
        session.setAttribute("listOfResultPencarianDataAset", pengajuanSetorDetailAsetList);
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

    public List<PengajuanSetorDetail> searchDataSessionPpnKeluaran() {
        logger.info("[PengajuanSetorAction.searchDataSessionPpnKeluaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPpnKeluaran] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");
    }

    public List<PengajuanSetorDetail> searchDataSessionPpnAset() {
        logger.info("[PengajuanSetorAction.searchDataSessionPpnAset] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanSetorAction.searchDataSessionPpnAset] stop process >>>");
        return (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataAset");
    }

    public PengajuanSetor editSessionPpnMasukan(String id,boolean check) {
        logger.info("[PengajuanSetorAction.editSessionPpnMasukan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        List<PengajuanSetorDetail> pengajuanSetorDetailList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukan");
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
            if (id.equalsIgnoreCase(pengajuanSetorDetail.getTransaksiId())){
                if (check){
                    pengajuanSetorDetail.setDibayar("Y");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().subtract(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPpnMasukan(addPengajuanSetor.getJumlahPpnMasukan().add(pengajuanSetorDetail.getJumlah()));
                }else{
                    pengajuanSetorDetail.setDibayar("N");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().add(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPpnMasukan(addPengajuanSetor.getJumlahPpnMasukan().subtract(pengajuanSetorDetail.getJumlah()));
                }
            }
        }
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));
        addPengajuanSetor.setStJumlahPpnMasukan(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPpnMasukan(),"###,###"));

        logger.info("[PengajuanSetorAction.editSessionPpnMasukan] stop process >>>");

        session.setAttribute("listOfResultPengajuanSetor", addPengajuanSetor);
        session.setAttribute("listOfResultPencarianDataMasukan", pengajuanSetorDetailList);

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

    public PengajuanSetor editSessionAset(String id,boolean check) {
        logger.info("[PengajuanSetorAction.editSessionAset] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor addPengajuanSetor = (PengajuanSetor) session.getAttribute("listOfResultPengajuanSetor");

        List<PengajuanSetorDetail> pengajuanSetorDetailList = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataAset");
        for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
            if (id.equalsIgnoreCase(pengajuanSetorDetail.getTransaksiId())){
                if (check){
                    pengajuanSetorDetail.setDibayar("Y");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().add(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPpnAset(addPengajuanSetor.getJumlahPpnAset().add(pengajuanSetorDetail.getJumlah()));
                }else{
                    pengajuanSetorDetail.setDibayar("N");
                    addPengajuanSetor.setJumlahSeluruhnya(addPengajuanSetor.getJumlahSeluruhnya().subtract(pengajuanSetorDetail.getJumlah()));
                    addPengajuanSetor.setJumlahPpnAset(addPengajuanSetor.getJumlahPpnAset().subtract(pengajuanSetorDetail.getJumlah()));
                }
            }
        }
        addPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahSeluruhnya(),"###,###"));
        addPengajuanSetor.setStJumlahPpnAset(CommonUtil.numbericFormat(addPengajuanSetor.getJumlahPpnAset(),"###,###"));

        logger.info("[PengajuanSetorAction.editSessionAset] stop process >>>");

        session.setAttribute("listOfResultPengajuanSetor", addPengajuanSetor);
        session.setAttribute("listOfResultPencarianDataAset", pengajuanSetorDetailList);

        return addPengajuanSetor;
    }

    public String saveAddPengajuanSetorPpn(){
        logger.info("[PengajuanSetorAction.saveAddPengajuanSetorPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        PengajuanSetor pengajuanSetor = getPengajuanSetor();
        List<PengajuanSetorDetail> pengajuanSetorDetailListMasukan = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataMasukan");
        List<PengajuanSetorDetail> pengajuanSetorDetailListKeluaran = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataKeluaran");
        List<PengajuanSetorDetail> pengajuanSetorDetailListAset = (List<PengajuanSetorDetail>) session.getAttribute("listOfResultPencarianDataAset");

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
            pengajuanSetorBoProxy.saveAddPengajuanSetorPpn(pengajuanSetor,pengajuanSetorDetailListMasukan,pengajuanSetorDetailListKeluaran,pengajuanSetorDetailListAset);
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

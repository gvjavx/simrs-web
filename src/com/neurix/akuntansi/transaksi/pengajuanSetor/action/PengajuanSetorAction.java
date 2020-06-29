package com.neurix.akuntansi.transaksi.pengajuanSetor.action;

import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.PengajuanSetorBo;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

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

package com.neurix.akuntansi.transaksi.budgetingeksploitasi.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.BudgetingPerhitunganBo;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.*;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by reza on 11/08/20.
 */
public class BgEksploitasiAction {
    private static transient Logger logger = Logger.getLogger(BgEksploitasiAction.class);
    private Budgeting budgeting;
    private String status;
    private String tipe;
    private String id;
    private String trans;
    private String tipeBudgeting = "eksploitasi";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public Budgeting getBudgeting() {
        return budgeting;
    }

    public void setBudgeting(Budgeting budgeting) {
        this.budgeting = budgeting;
    }

    public String initForm(){
        String userBranchId = CommonUtil.userBranchLogin();
        Budgeting budgeting = new Budgeting();
        budgeting.setFlagKp(userBranchId.equalsIgnoreCase(CommonConstant.ID_KANPUS) ? "Y" : "N");
        budgeting.setBranchId(userBranchId);
        setBudgeting(budgeting);
        eraseAllSession();
        return "search";
    }

    private void eraseAllSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfCoa");
        session.removeAttribute("listOfDetail");
        session.removeAttribute("listOfPengadaan");
        session.removeAttribute("listOfParam");
        session.removeAttribute("listOfPerhitungan");
    }

    public List<Budgeting> getListBranchBudgeting(String tahun) {

        logger.info("[BgEksploitasiAction.getListBranchBudgeting] START >> ");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        return budgetingPerhitunganBo.getBranchBudgeting(tahun);

    }

    private List<Budgeting> convertParamToListBudgeting(List<ParameterBudgeting> listParam, String tahun, String unit){
        BudgetingAction budgetingAction = new BudgetingAction();
        List<Budgeting> budgetingList = new ArrayList<>();
        if (listParam != null && listParam.size() > 0){
            for (ParameterBudgeting param : listParam){
                // membuat object budgeting baru;
                // convert null to 0 masing - masing nilai
                Budgeting budgeting = new Budgeting();
                budgeting = budgetingAction.kosongkanNilaiBudgeting(budgeting);
                if (budgetingList.size() == 0){
                    budgeting.setKodeRekening(getKodeRekeningById(param.getRekeningId()).getKodeRekening());
                    budgeting.setTahun(tahun);
                    budgeting.setBranchId(unit);
                    budgeting.setRekeningId(param.getRekeningId());
                    budgeting.setTipe("bulanan");
                    budgeting.setNilaiTotal(param.getNilaiTotal());
                    if ("januari".equalsIgnoreCase(param.getTipe()))
                        budgeting.setJanuari(param.getNilaiTotal());
                    if ("februari".equalsIgnoreCase(param.getTipe()))
                        budgeting.setFebruari(param.getNilaiTotal());
                    if ("maret".equalsIgnoreCase(param.getTipe()))
                        budgeting.setMaret(param.getNilaiTotal());
                    if ("april".equalsIgnoreCase(param.getTipe()))
                        budgeting.setApril(param.getNilaiTotal());
                    if ("mei".equalsIgnoreCase(param.getTipe()))
                        budgeting.setMei(param.getNilaiTotal());
                    if ("juni".equalsIgnoreCase(param.getTipe()))
                        budgeting.setJuni(param.getNilaiTotal());
                    if ("juli".equalsIgnoreCase(param.getTipe()))
                        budgeting.setJuli(param.getNilaiTotal());
                    if ("agustus".equalsIgnoreCase(param.getTipe()))
                        budgeting.setAgustus(param.getNilaiTotal());
                    if ("september".equalsIgnoreCase(param.getTipe()))
                        budgeting.setSeptember(param.getNilaiTotal());
                    if ("oktober".equalsIgnoreCase(param.getTipe()))
                        budgeting.setOktober(param.getNilaiTotal());
                    if ("november".equalsIgnoreCase(param.getTipe()))
                        budgeting.setNovember(param.getNilaiTotal());
                    if ("desember".equalsIgnoreCase(param.getTipe()))
                        budgeting.setDesember(param.getNilaiTotal());
                    budgetingList.add(budgeting);
                } else {
                    boolean notFound = true;
                    for (Budgeting bg : budgetingList){
                        if (param.getRekeningId().equalsIgnoreCase(bg.getRekeningId())){
                            bg.setNilaiTotal(bg.getNilaiTotal().add(param.getNilaiTotal()));
                            if ("januari".equalsIgnoreCase(param.getTipe()))
                                bg.setJanuari(bg.getJanuari().add(param.getNilaiTotal()));
                            if ("februari".equalsIgnoreCase(param.getTipe()))
                                bg.setFebruari(bg.getFebruari().add(param.getNilaiTotal()));
                            if ("maret".equalsIgnoreCase(param.getTipe()))
                                bg.setMaret(bg.getMaret().add(param.getNilaiTotal()));
                            if ("april".equalsIgnoreCase(param.getTipe()))
                                bg.setApril(bg.getApril().add(param.getNilaiTotal()));
                            if ("mei".equalsIgnoreCase(param.getTipe()))
                                bg.setMei(bg.getMei().add(param.getNilaiTotal()));
                            if ("juni".equalsIgnoreCase(param.getTipe()))
                                bg.setJuni(bg.getJuni().add(param.getNilaiTotal()));
                            if ("juli".equalsIgnoreCase(param.getTipe()))
                                bg.setJuli(bg.getJuli().add(param.getNilaiTotal()));
                            if ("agustus".equalsIgnoreCase(param.getTipe()))
                                bg.setAgustus(bg.getAgustus().add(param.getNilaiTotal()));
                            if ("september".equalsIgnoreCase(param.getTipe()))
                                bg.setSeptember(bg.getSeptember().add(param.getNilaiTotal()));
                            if ("oktober".equalsIgnoreCase(param.getTipe()))
                                bg.setOktober(bg.getOktober().add(param.getNilaiTotal()));
                            if ("november".equalsIgnoreCase(param.getTipe()))
                                bg.setNovember(bg.getNovember().add(param.getNilaiTotal()));
                            if ("desember".equalsIgnoreCase(param.getTipe()))
                                bg.setDesember(bg.getDesember().add(param.getNilaiTotal()));
                            notFound = false;
                            break;
                        }
                    }
                    if (notFound){
                        budgeting.setKodeRekening(getKodeRekeningById(param.getRekeningId()).getKodeRekening());
                        budgeting.setTahun(tahun);
                        budgeting.setBranchId(unit);
                        budgeting.setRekeningId(param.getRekeningId());
                        budgeting.setTipe("bulanan");
                        budgeting.setNilaiTotal(param.getNilaiTotal());
                        if ("januari".equalsIgnoreCase(param.getTipe()))
                            budgeting.setJanuari(param.getNilaiTotal());
                        if ("februari".equalsIgnoreCase(param.getTipe()))
                            budgeting.setFebruari(param.getNilaiTotal());
                        if ("maret".equalsIgnoreCase(param.getTipe()))
                            budgeting.setMaret(param.getNilaiTotal());
                        if ("april".equalsIgnoreCase(param.getTipe()))
                            budgeting.setApril(param.getNilaiTotal());
                        if ("mei".equalsIgnoreCase(param.getTipe()))
                            budgeting.setMei(param.getNilaiTotal());
                        if ("juni".equalsIgnoreCase(param.getTipe()))
                            budgeting.setJuni(param.getNilaiTotal());
                        if ("juli".equalsIgnoreCase(param.getTipe()))
                            budgeting.setJuli(param.getNilaiTotal());
                        if ("agustus".equalsIgnoreCase(param.getTipe()))
                            budgeting.setAgustus(param.getNilaiTotal());
                        if ("september".equalsIgnoreCase(param.getTipe()))
                            budgeting.setSeptember(param.getNilaiTotal());
                        if ("oktober".equalsIgnoreCase(param.getTipe()))
                            budgeting.setOktober(param.getNilaiTotal());
                        if ("november".equalsIgnoreCase(param.getTipe()))
                            budgeting.setNovember(param.getNilaiTotal());
                        if ("desember".equalsIgnoreCase(param.getTipe()))
                            budgeting.setDesember(param.getNilaiTotal());
                        budgetingList.add(budgeting);
                    }
                }
            }
        }
        return budgetingList;
    }

    private List<BudgetingDetail> convertParamToBudgetingDetail(List<ParameterBudgeting> listParam, String tahun, String unit){
        List<BudgetingDetail> budgetingList = new ArrayList<>();
        if (listParam != null && listParam.size() > 0){
            for (ParameterBudgeting param : listParam){
                // membuat object budgeting baru;
                // convert null to 0 masing - masing nilai
                BudgetingDetail budgetingDetail = new BudgetingDetail();
                budgetingDetail.setMasterId(param.getMasterId());
                budgetingDetail.setDivisiId(param.getDivisiId());
                budgetingDetail.setQty(new BigInteger(String.valueOf(1)));
                budgetingDetail.setNilai(param.getNilaiTotal());
                budgetingDetail.setSubTotal(param.getNilaiTotal().multiply(new BigDecimal(budgetingDetail.getQty())));
                budgetingDetail.setTipe(param.getTipe());
                budgetingDetail.setRekeningId(param.getRekeningId());
                budgetingList.add(budgetingDetail);
            }
        }
        return budgetingList;
    }

    private ImKodeRekeningEntity getKodeRekeningById(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        return kodeRekeningBo.getKodeRekeningById(id);
    }

    public CrudResponse approveFinal(String tahun){
        logger.info("[BgEksploitasiAction.approveFinal] START >>> ");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        Branch branch = new Branch();
        branch.setFlag("Y");
        List<Branch> branchesList = branchBo.getByCriteria(branch);
        if (branchesList.size() > 0){
            for (Branch dataBranch : branchesList){

                ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
                parameterBudgeting.setBranchId(dataBranch.getBranchId());
                parameterBudgeting.setTahun(tahun);

                List<ParameterBudgeting> nilaParams = budgetingPerhitunganBo.getNilaiParameterByNilaiParam(parameterBudgeting);
                if (nilaParams != null && nilaParams.size() > 0){

                    List<Budgeting> budgetingList =  convertParamToListBudgeting(nilaParams, tahun, dataBranch.getBranchId());
                    List<BudgetingDetail> budgetingDetailList = convertParamToBudgetingDetail(nilaParams, tahun, dataBranch.getBranchId());

                    Budgeting budgeting = new Budgeting();
                    budgeting.setBranchId(dataBranch.getBranchId());
                    budgeting.setTahun(tahun);
                    budgeting.setStatus("APPROVE_FINAL");
                    budgeting.setTipe("bulanan");
                    budgeting.setFlag("Y");
                    budgeting.setAction("C");
                    budgeting.setCreatedDate(time);
                    budgeting.setCreatedWho(userLogin);
                    budgeting.setLastUpdate(time);
                    budgeting.setLastUpdateWho(userLogin);

                    try {
                        budgetingBo.saveAddBudgeting(budgetingList, budgetingDetailList, new ArrayList<>(), "FINAL", budgeting);
                    } catch (GeneralBOException e){
                        logger.info("[BgPendapatanAction.approveFinal] ERROR ", e);
                        response.setStatus("error");
                        response.setMsg("[BgPendapatanAction.approveFinal] ERROR " + e);
                        return response;
                    }
                }
            }
        }

        response.setStatus("success");
        logger.info("[BgEksploitasiAction.approveFinal] END <<< ");
        return response;
    }

}

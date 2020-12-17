package com.neurix.akuntansi.transaksi.budgetingeksploitasi.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunJenisBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgeting;
import com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.BudgetingPerhitunganBo;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.*;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
                    if ("januari".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setJanuari(param.getNilaiTotal());
                    if ("februari".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setFebruari(param.getNilaiTotal());
                    if ("maret".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setMaret(param.getNilaiTotal());
                    if ("april".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setApril(param.getNilaiTotal());
                    if ("mei".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setMei(param.getNilaiTotal());
                    if ("juni".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setJuni(param.getNilaiTotal());
                    if ("juli".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setJuli(param.getNilaiTotal());
                    if ("agustus".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setAgustus(param.getNilaiTotal());
                    if ("september".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setSeptember(param.getNilaiTotal());
                    if ("oktober".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setOktober(param.getNilaiTotal());
                    if ("november".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setNovember(param.getNilaiTotal());
                    if ("desember".equalsIgnoreCase(param.getPeriode()))
                        budgeting.setDesember(param.getNilaiTotal());
                    budgetingList.add(budgeting);
                } else {
                    boolean notFound = true;
                    for (Budgeting bg : budgetingList){
                        if (param.getRekeningId().equalsIgnoreCase(bg.getRekeningId())){
                            bg.setNilaiTotal(bg.getNilaiTotal().add(param.getNilaiTotal()));
                            if ("januari".equalsIgnoreCase(param.getPeriode()))
                                bg.setJanuari(bg.getJanuari().add(param.getNilaiTotal()));
                            if ("februari".equalsIgnoreCase(param.getPeriode()))
                                bg.setFebruari(bg.getFebruari().add(param.getNilaiTotal()));
                            if ("maret".equalsIgnoreCase(param.getPeriode()))
                                bg.setMaret(bg.getMaret().add(param.getNilaiTotal()));
                            if ("april".equalsIgnoreCase(param.getPeriode()))
                                bg.setApril(bg.getApril().add(param.getNilaiTotal()));
                            if ("mei".equalsIgnoreCase(param.getPeriode()))
                                bg.setMei(bg.getMei().add(param.getNilaiTotal()));
                            if ("juni".equalsIgnoreCase(param.getPeriode()))
                                bg.setJuni(bg.getJuni().add(param.getNilaiTotal()));
                            if ("juli".equalsIgnoreCase(param.getPeriode()))
                                bg.setJuli(bg.getJuli().add(param.getNilaiTotal()));
                            if ("agustus".equalsIgnoreCase(param.getPeriode()))
                                bg.setAgustus(bg.getAgustus().add(param.getNilaiTotal()));
                            if ("september".equalsIgnoreCase(param.getPeriode()))
                                bg.setSeptember(bg.getSeptember().add(param.getNilaiTotal()));
                            if ("oktober".equalsIgnoreCase(param.getPeriode()))
                                bg.setOktober(bg.getOktober().add(param.getNilaiTotal()));
                            if ("november".equalsIgnoreCase(param.getPeriode()))
                                bg.setNovember(bg.getNovember().add(param.getNilaiTotal()));
                            if ("desember".equalsIgnoreCase(param.getPeriode()))
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
                        if ("januari".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setJanuari(param.getNilaiTotal());
                        if ("februari".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setFebruari(param.getNilaiTotal());
                        if ("maret".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setMaret(param.getNilaiTotal());
                        if ("april".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setApril(param.getNilaiTotal());
                        if ("mei".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setMei(param.getNilaiTotal());
                        if ("juni".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setJuni(param.getNilaiTotal());
                        if ("juli".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setJuli(param.getNilaiTotal());
                        if ("agustus".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setAgustus(param.getNilaiTotal());
                        if ("september".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setSeptember(param.getNilaiTotal());
                        if ("oktober".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setOktober(param.getNilaiTotal());
                        if ("november".equalsIgnoreCase(param.getPeriode()))
                            budgeting.setNovember(param.getNilaiTotal());
                        if ("desember".equalsIgnoreCase(param.getPeriode()))
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
                if ("INV".equalsIgnoreCase(param.getMasterId())){
                    budgetingDetail.setDivisiId("INVS");
                    budgetingDetail.setMasterId("");
                } else if ("BYA".equalsIgnoreCase(param.getMasterId())){
                    budgetingDetail.setDivisiId(param.getDivisiId());
                    budgetingDetail.setMasterId("");
                }else {
                    budgetingDetail.setDivisiId(param.getDivisiId());
                    budgetingDetail.setMasterId(param.getMasterId());
                }
                budgetingDetail.setQty(new BigInteger(String.valueOf(1)));
                budgetingDetail.setNilai(param.getNilaiTotal());
                budgetingDetail.setSubTotal(param.getNilaiTotal().multiply(new BigDecimal(budgetingDetail.getQty())));
                budgetingDetail.setTipe(param.getPeriode());
                budgetingDetail.setRekeningId(param.getRekeningId());
                budgetingDetail.setIdNilaiParam(param.getIdNilaiParameter());
                budgetingList.add(budgetingDetail);
            }
        }
        return budgetingList;
    }

    private List<BudgetingPengadaan> convertParamToBudgetingPengadaan(List<ParameterBudgeting> listParam, String tahun, String unit){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        List<BudgetingPengadaan> budgetingList = new ArrayList<>();
        if (listParam != null && listParam.size() > 0){
            for (ParameterBudgeting param : listParam){

                ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
                parameterBudgeting.setIdNilaiParameter(param.getIdNilaiParameter());
                parameterBudgeting.setFlag("Y");

                List<ItAkunNilaiParameterPengadaaanEntity> pengadaaanEntities = budgetingPerhitunganBo.getListEntityNilaiParameterPengadaan(parameterBudgeting);
                if (pengadaaanEntities != null && pengadaaanEntities.size() > 0){

                    for (ItAkunNilaiParameterPengadaaanEntity pengadaaanEntity : pengadaaanEntities){

                        BudgetingPengadaan budgetingPengadaan = new BudgetingPengadaan();
                        budgetingPengadaan.setNamPengadaan(pengadaaanEntity.getNama());
                        budgetingPengadaan.setNilai(pengadaaanEntity.getNilai());
                        budgetingPengadaan.setQty(pengadaaanEntity.getQty());
                        budgetingPengadaan.setSubTotal(param.getNilaiTotal());
                        budgetingPengadaan.setTipe(param.getTipe());
                        budgetingPengadaan.setRekeningId(param.getRekeningId());
                        budgetingPengadaan.setIdNilaiParam(param.getIdNilaiParameter());
                        budgetingList.add(budgetingPengadaan);
                    }
                }
                // membuat object budgeting baru;
                // convert null to 0 masing - masing nilai
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
                    List<BudgetingPengadaan> budgetingPengadaans = convertParamToBudgetingPengadaan(nilaParams, tahun, dataBranch.getBranchId());

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
                    budgeting.setJenis("ADD");

                    try {
                        budgetingBo.saveAddBudgeting(budgetingList, budgetingDetailList, budgetingPengadaans, "FINAL", budgeting);
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

    public List<ParameterBudgeting> getJenisBudgeting(String tahun, String branchId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
        parameterBudgeting.setFlag("Y");
        List<ImAkunJenisBudgetingEntity> jenisBudgetingEntities =  budgetingPerhitunganBo.getListEntityJenisBudgetingByCriteria(parameterBudgeting);
        List<ParameterBudgeting> results = new ArrayList<>();

        if (jenisBudgetingEntities.size() > 0){
            int i = 0;
            BigDecimal jmlPendapatan = new BigDecimal(0);
            BigDecimal jmlBiaya = new BigDecimal(0);
            for (ImAkunJenisBudgetingEntity jenisBudgetingEntity : jenisBudgetingEntities){
                ParameterBudgeting result = new ParameterBudgeting();
                result.setIdJenisBudgeting(jenisBudgetingEntity.getId());
                result.setNama(jenisBudgetingEntity.getNamaJenis());

                List<ParameterBudgeting> listKategoriBudgeting = getListKategoriBudgeting(tahun, branchId, jenisBudgetingEntity.getId());
                if (listKategoriBudgeting.size() > 0){
                    BigDecimal nilaiTotal = hitungTotalFromListBudgeting(listKategoriBudgeting);
                    if ("PDT".equalsIgnoreCase(jenisBudgetingEntity.getId())){
                        jmlPendapatan = jmlPendapatan.add(nilaiTotal);
                    } else {
                        jmlBiaya = jmlBiaya.add(nilaiTotal);
                    }
                    result.setNilaiTotal(nilaiTotal);
                }

                results.add(result);
                i++;

                // tambahkan hasil perhitungan laba rugi
                if (i == jenisBudgetingEntities.size()){
                    result = new ParameterBudgeting();
                    BigDecimal nilaiTotal = jmlPendapatan.subtract(jmlBiaya);
                    if (nilaiTotal.compareTo(new BigDecimal(0)) == -1){
                        result.setIdJenisBudgeting("rugi");
                        result.setNama("Rugi");
                        result.setNilaiTotal(nilaiTotal);
                    } else {
                        result.setIdJenisBudgeting("laba");
                        result.setNama("Laba");
                        result.setNilaiTotal(nilaiTotal);
                    }
                    results.add(result);
                }
            }
        }
        return results;
    }


    public List<ParameterBudgeting> getListKategoriBudgeting(String tahun, String branchId, String idJenisBudgeting){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        List<ParameterBudgeting> listKategori =  budgetingPerhitunganBo.getListSumOfKategoriBudgeting(idJenisBudgeting, tahun, branchId);
        return listKategori;
    }

    private BigDecimal hitungTotalFromListBudgeting(List<ParameterBudgeting> params){
        BigDecimal nilaiTotal = new BigDecimal(0);
        for (ParameterBudgeting param : params){
            nilaiTotal = nilaiTotal.add(param.getNilaiTotal());
        }
        return nilaiTotal;
    }

    public String checkIsAvaliable(String tahun){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        return budgetingBo.ceckAvailBudgetingByTahun(tahun);
    }

}

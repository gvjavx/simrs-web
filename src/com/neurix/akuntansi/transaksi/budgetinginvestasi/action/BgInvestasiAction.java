package com.neurix.akuntansi.transaksi.budgetinginvestasi.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by reza on 11/08/20.
 */
public class BgInvestasiAction {
    private static transient Logger logger = Logger.getLogger(BgInvestasiAction.class);
    private Budgeting budgeting;
    private String status;
    private String tipe;
    private String id;
    private String trans;
    private String tipeBudgeting = "investasi";

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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfCoa");
        session.removeAttribute("listOfDetail");
        session.removeAttribute("listOfPengadaan");
        return "search";
    }

    public CrudResponse getSearchListBudgeting(String tahun, String unit){
        logger.info("[BgInvestasiAction.getSearchListBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        Budgeting budgeting = new Budgeting();
        budgeting.setTahun(tahun);
        budgeting.setBranchId(unit);
        budgeting.setTipeBudgeting(this.tipeBudgeting);
        budgeting.setRekeningIdList(kodeRekeningBo.getListRekeningIdsByTipeBudgeting(this.tipeBudgeting));

        BudgetingAction budgetingAction = new BudgetingAction();

        // get last Status
        Budgeting budgetingStatus = budgetingAction.findLastStatus(budgeting.getBranchId(), budgeting.getTahun(), this.tipeBudgeting);
        if (budgetingStatus != null){
            budgeting.setStatus(budgetingStatus.getStatus());
        }

        List<Budgeting> budgetings = new ArrayList<>();

        CrudResponse response = new CrudResponse();
        try {
            budgetings = budgetingBo.getSearchByCriteria(budgeting);
            response.setStatus("success");
            response.setList(budgetings);
        } catch (GeneralBOException e){
            logger.error("[BudgetingAction.getSearchListBudgeting] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[BudgetingAction.getSearchListBudgeting] ERROR. "+e.getMessage());
            return response;
        }


        logger.info("[BgInvestasiAction.getSearchListBudgeting] END <<<");
        return response;
    }

    public String add(){
        logger.info("[BgInvestasiAction.add] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        Budgeting budgeting = getBudgeting();
        Budgeting budgetingNew = new Budgeting();
        budgetingNew.setTipeBudgeting(this.tipeBudgeting);
        if (budgeting != null){
            budgetingNew.setTahun(budgeting.getTahun());
            budgetingNew.setBranchId(budgeting.getBranchId());
            budgetingNew.setTipe(budgeting.getTipe());
        }
        if (budgetingNew.getBranchId() == null || "".equalsIgnoreCase(budgetingNew.getBranchId())){
            budgetingNew.setFlagKp(branchId.equalsIgnoreCase(CommonConstant.ID_KANPUS) ? "Y" : "N");
            budgetingNew.setBranchId(branchId);
        }

        String tipe = budgetingBo.checkLastTipeBudgeting();
        if (!"".equalsIgnoreCase(tipe)){
            budgetingNew.setTipe(tipe);
            budgetingNew.setFlagDisable("Y");
        }


        // sset tahun, unit, tipe
        if (budgetingSessionList != null && budgeting != null){
            for (Budgeting sessionBudgeting : budgetingSessionList){
                sessionBudgeting.setTahun(budgeting.getTahun());
                sessionBudgeting.setBranchId(budgeting.getBranchId());
                sessionBudgeting.setTipe(budgeting.getTipe());

                if ("add".equalsIgnoreCase(this.status)){
                    sessionBudgeting.setStatus("ADD_DRAFT");
                }
            }
        }

        logger.info("[BudgetingAction.add] END <<<");
        if ("add".equalsIgnoreCase(this.status) && "detail".equalsIgnoreCase(this.tipe)){
            setBudgeting(budgetingNew);
            return "edit";
        } else if ("add".equalsIgnoreCase(this.status)){
            setBudgeting(budgetingNew);
            return "add";
        } else {
            setBudgeting(budgetingNew);
            session.removeAttribute("listOfCoa");
            session.setAttribute("listOfCoa", new ArrayList<>());
            return "add";
        }
    }

    public String edit(){
        logger.info("[BgInvestasiAction.edit] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");
        List<BudgetingPengadaan> sessionPengadaan = (List<BudgetingPengadaan>) session.getAttribute("listOfPengadaan");

        Budgeting budgeting = getBudgeting();

        Budgeting budgetingNew = new Budgeting();
        budgetingNew.setTahun(budgeting.getTahun());
        budgetingNew.setTipe(budgeting.getTipe());
        budgetingNew.setBranchId(budgeting.getBranchId());

        if (budgetingNew.getBranchId() != null && !"".equalsIgnoreCase(budgetingNew.getBranchId())){
            Branch branch = branchBo.getBranchById(budgetingNew.getBranchId(), "Y");
            if (branch != null){
                budgetingNew.setBranchName(branch.getBranchName());
            }
        }

        if (budgetingSessionList == null){
            budgetingSessionList = new ArrayList<>();
        }
        if (sessionDetail == null){
            sessionDetail = new ArrayList<>();
        }
        if (sessionPengadaan == null){
            sessionPengadaan = new ArrayList<>();
        }


        if (!"".equalsIgnoreCase(this.id) && this.id != null){
            // medapatakan parent
            List<Budgeting> budgetingList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(this.id)).collect(Collectors.toList());
            if (budgetingList.size() > 0){
                budgetingNew.setKodeRekening(budgetingList.get(0).getKodeRekening());
                budgetingNew.setNamaKodeRekening(budgetingList.get(0).getNamaKodeRekening());
                budgetingNew.setParentId(budgetingList.get(0).getParentId());
                budgetingNew.setTipeCoa(budgetingList.get(0).getTipeCoa());
                budgetingNew.setFlagMaster(budgetingList.get(0).getFlagMaster());
                budgetingNew.setFlagDivisi(budgetingList.get(0).getFlagDivisi());

                if (!"-".equalsIgnoreCase(budgetingNew.getParentId()) && !"".equalsIgnoreCase(budgetingNew.getParentId())){
                    List<Budgeting> parentList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgetingNew.getParentId())).collect(Collectors.toList());
                    if (parentList.size() > 0){
                        budgetingNew.setKodeParent(parentList.get(0).getKodeRekening());
                        budgetingNew.setNamaParent(parentList.get(0).getNamaKodeRekening());
                    }
                }
            }

            if (sessionDetail != null){

                // menncari jika ada list detail
                List<BudgetingDetail> detailList = sessionDetail.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(this.id)).collect(Collectors.toList());
                if (detailList.size() > 0 && budgetingList.get(0).getNilaiTotal().compareTo(new BigDecimal(0)) == 1) {

                    // jika ada pada listOfDetail maka pindahkan ke listOfDetailEdit
                    session.removeAttribute("listOfDetailEdit");
                    session.setAttribute("listOfDetailEdit", detailList);
                } else {
                    // jika tidak ada maka hapus;
                    session.removeAttribute("listOfDetailEdit");
                }
            } else {
                session.removeAttribute("listOfDetail");
                session.removeAttribute("listOfDetailEdit");
            }

            logger.info("[BudgetingAction.edit] END <<<");

            setBudgeting(budgetingNew);
            if ("add".equalsIgnoreCase(this.status) || "edit".equalsIgnoreCase(this.status) && this.id != null){
                return "edit_detail";
            } else {
                session.removeAttribute("listOfDetail");
                session.setAttribute("listOfDetail", new ArrayList<>());
                return "edit_detail";
            }
        } else {

            if (budgetingSessionList.size() == 0){
                if (this.trans != null){
                    List<Budgeting> listOfBudgeting = new ArrayList<>();
                    BudgetingAction budgetingAction = new BudgetingAction();
                    for (StatusBudgeting statusBudgeting : budgetingAction.listOfStatusBudgeting()){

                        Budgeting searchBudgeting = new Budgeting();
                        searchBudgeting.setTahun(budgeting.getTahun());
                        searchBudgeting.setBranchId(budgeting.getBranchId());
                        searchBudgeting.setStatus(statusBudgeting.getStatusDetail());
                        searchBudgeting.setTipeBudgeting(this.tipeBudgeting);
                        searchBudgeting.setRekeningIdList(kodeRekeningBo.getListRekeningIdsByTipeBudgeting(this.tipeBudgeting));

                        List<Budgeting> budgetingList = budgetingBo.getSearchByCriteria(searchBudgeting);
                        if (budgetingList.size() > 0){

                            List<BudgetingDetail> budgetingDetailList = new ArrayList<>();
                            for (Budgeting budgetingData : budgetingList){

                                // Mencari Budgeting Detail
                                List<BudgetingDetail> budgetingDetails = budgetingBo.getListBudgetingDetailByNoBudgeting(budgetingData.getIdBudgeting());
                                if (budgetingDetails.size() > 0){
                                    budgetingDetailList.addAll(budgetingDetails);

                                    for (BudgetingDetail budgetingDetail : budgetingDetails){

                                        if ("bulanan".equalsIgnoreCase(budgetingData.getTipe())){
                                            if (budgetingData.getListPeriode().size() > 0){
                                                List<BudgetingPeriode> periodes = budgetingData.getListPeriode().stream().filter(p -> p.getNamaBulan().equalsIgnoreCase(budgetingDetail.getTipe())).collect(Collectors.toList());
                                                if (periodes.size() > 0){
                                                    budgetingDetail.setFlagEdit("N");
                                                }
                                            }
                                        }

                                        if ("quartal".equalsIgnoreCase(budgetingData.getTipe())){
                                            if (budgetingData.getListPeriode().size() > 0){
                                                List<BudgetingPeriode> periodes = budgetingData.getListPeriode().stream().filter(p -> p.getKuartal().equalsIgnoreCase(budgetingDetail.getTipe())).collect(Collectors.toList());
                                                if (periodes.size() > 0){
                                                    budgetingDetail.setFlagEdit("N");
                                                }
                                            }
                                        }

                                        if ("semester".equalsIgnoreCase(budgetingData.getTipe())){
                                            if (budgetingData.getListPeriode().size() > 0){
                                                List<BudgetingPeriode> periodes = budgetingData.getListPeriode().stream().filter(p -> p.getSemester().equalsIgnoreCase(budgetingDetail.getTipe())).collect(Collectors.toList());
                                                if (periodes.size() > 0){
                                                    budgetingDetail.setFlagEdit("N");
                                                }
                                            }
                                        }

                                        // Mencari Budgeting Pengadaan
                                        List<BudgetingPengadaan> pengadaans = budgetingBo.getListBudgetingPengadaanNoDetail(budgetingDetail.getIdBudgetingDetail());
                                        if (pengadaans.size() > 0){
                                            sessionPengadaan.addAll(pengadaans);
                                        }
                                    }
                                }
                            }

                            // set budgeting
                            searchBudgeting.setTipe(budgetingList.get(0).getTipe());
                            setBudgeting(searchBudgeting);

                            session.removeAttribute("listOfPengadaan");
                            session.setAttribute("listOfPengadaan", sessionPengadaan);

                            session.removeAttribute("listOfDetail");
                            session.setAttribute("listOfDetail", budgetingDetailList);

                            session.removeAttribute("listOfCoa");
                            session.setAttribute("listOfCoa", budgetingList);
                            listOfBudgeting.addAll(budgetingList);
                            break;
                        }
                    }

                    logger.info("[BudgetingAction.edit] END <<<");
                    // jika tidak ada return ke add;
                    if (listOfBudgeting.size() == 0){
                        return add();
                    }
                }
            } else {
                Budgeting searchBudgeting = new Budgeting();
                searchBudgeting.setTahun(budgeting.getTahun());
                searchBudgeting.setBranchId(budgeting.getBranchId());
                searchBudgeting.setTipe(budgetingSessionList.get(0).getTipe());
                setBudgeting(searchBudgeting);
            }

            logger.info("[BgInvestasiAction.edit] END <<<");
            return "edit";
        }
    }
}

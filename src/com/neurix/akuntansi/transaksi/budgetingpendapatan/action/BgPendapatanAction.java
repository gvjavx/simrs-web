package com.neurix.akuntansi.transaksi.budgetingpendapatan.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
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
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by reza on 11/08/20.
 */
public class BgPendapatanAction {
    private static transient Logger logger = Logger.getLogger(BgPendapatanAction.class);
    private Budgeting budgeting;
    private String status;
    private String tipe;
    private String id;
    private String trans;
    private String tipeBudgeting = "pendapatan";

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
        session.removeAttribute("listOfNilaiParam");
    }

    public CrudResponse getSearchListBudgeting(String tahun, String unit){
        logger.info("[BgPendapatanAction.getSearchListBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        Budgeting budgeting = new Budgeting();
        budgeting.setTahun(tahun);
        budgeting.setBranchId(unit);
        budgeting.setRekeningIdList(kodeRekeningBo.getListRekeningIdsByTipeBudgeting(this.tipeBudgeting));

        BudgetingAction budgetingAction = new BudgetingAction();

        // get last Status
        Budgeting budgetingStatus = budgetingAction.findLastStatus(budgeting.getBranchId(), budgeting.getTahun(), "pendapatan");
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
            logger.error("[BgPendapatanAction.getSearchListBudgeting] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[BgPendapatanAction.getSearchListBudgeting] ERROR. "+e.getMessage());
            return response;
        }


        logger.info("[BgPendapatanAction.getSearchListBudgeting] END <<<");
        return response;
    }

    public String add(){
        logger.info("[BgPendapatanAction.add] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        Budgeting budgeting = getBudgeting();
        Budgeting budgetingNew = new Budgeting();
        budgetingNew.setTipeBudgeting(this.tipeBudgeting);
        if (budgeting != null){
            budgetingNew.setTahun(budgeting.getTahun());
            budgetingNew.setBranchId(budgeting.getBranchId());
            budgetingNew.setTipe(budgeting.getTipe());
            budgetingNew.setIdKategoriBudgeting(budgeting.getIdKategoriBudgeting());
            budgetingNew.setNamaKategori(budgeting.getNamaKategori());
        }
        if (budgetingNew.getBranchId() == null || "".equalsIgnoreCase(budgetingNew.getBranchId())){
            budgetingNew.setFlagKp(branchId.equalsIgnoreCase(CommonConstant.ID_KANPUS) ? "Y" : "N");
            budgetingNew.setBranchId(branchId);
        }

        Branch branch = branchBo.getBranchById(budgetingNew.getBranchId(), "Y");
        if (branch != null){
            budgetingNew.setBranchName(branch.getBranchName());
        }
        String tipe = budgetingBo.checkLastTipeBudgeting();
        if (!"".equalsIgnoreCase(tipe)){
            budgetingNew.setTipe(tipe);
            budgetingNew.setFlagDisable("Y");
        }


        // sset tahun, unit, tipe
//        if (budgetingSessionList != null && budgeting != null){
//            for (Budgeting sessionBudgeting : budgetingSessionList){
//                sessionBudgeting.setTahun(budgeting.getTahun());
//                sessionBudgeting.setBranchId(budgeting.getBranchId());
//                sessionBudgeting.setTipe(budgeting.getTipe());
//
//                if ("add".equalsIgnoreCase(this.status)){
//                    sessionBudgeting.setStatus("ADD_DRAFT");
//                }
//            }
//        }

        logger.info("[BgPendapatanAction.add] END <<<");
        if ("add".equalsIgnoreCase(this.status) && "detail".equalsIgnoreCase(this.tipe)){
            setBudgeting(budgetingNew);
            return "edit";
        } else if ("add".equalsIgnoreCase(this.status)){
            setBudgeting(budgetingNew);
            return "add";
        } else {
            setBudgeting(budgetingNew);
            if ("Y".equalsIgnoreCase(budgetingNew.getFlagKp())){
                eraseAllSession();
                return "add_kp";
            } else {
                return "add";
            }
        }
    }

    public String edit(){
        logger.info("[BgPendapatanAction.edit] START >>>");

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

            logger.info("[BgPendapatanAction.edit] END <<<");

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

                    logger.info("[BgPendapatanAction.edit] END <<<");
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

            logger.info("[BgPendapatanAction.edit] END <<<");
            return "edit";
        }
    }

    public CrudResponse getListKategoriParameter(String idJenis, String tahun, String branchId){
        logger.info("[BgPendapatanAction.getListKategoriParameter] START >>>");

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        KategoriParameter kategoriParameter = new KategoriParameter();
        kategoriParameter.setIdJenisBudgeting(idJenis);
        kategoriParameter.setTahun(tahun);
        kategoriParameter.setBranchId(branchId);

        List<KategoriParameter> kategoriParameters = new ArrayList<>();

        try {
            kategoriParameters = budgetingPerhitunganBo.getListKategoriParameter(kategoriParameter);
            response.setStatus("success");
            response.setList(kategoriParameters);
        } catch (GeneralBOException e){
            logger.error("[BgPendapatanAction.getListParametersBudgeting] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[BgPendapatanAction.getListParametersBudgeting] ERROR " + e);
            return response;
        }

        logger.info("[BgPendapatanAction.getListParametersBudgeting] END <<<");
        return response;
    }

    public List<ParameterBudgeting> getListParameterBudgeting(String idJenis, String tahun, String branchId, String tipe){
        logger.info("[BgPendapatanAction.getListParametersBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionParam = (List<ParameterBudgeting>) session.getAttribute("listOfParam");
        List<ParameterBudgeting> listParamBudgeting = new ArrayList<>();

        if (sessionParam != null && !"new".equalsIgnoreCase(tipe)){
            // jika session ada
            listParamBudgeting = sessionParam;
        } else {

            // jika session kosong;
            // cari data dri table;
            ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
            parameterBudgeting.setIdJenisBudgeting(idJenis);
            parameterBudgeting.setBranchId(branchId);
            parameterBudgeting.setTahun(tahun);
            try {
                listParamBudgeting = budgetingPerhitunganBo.getSearchListParameterBudgeting(parameterBudgeting);
            } catch (GeneralBOException e){
                logger.error("[BgPendapatanAction.getListParametersBudgeting] ERROR", e);
            }

            // set Session
            sessionParam = new ArrayList<>();
            sessionParam.addAll(listParamBudgeting);
            session.setAttribute("listOfParam", sessionParam);
        }

        logger.info("[BgPendapatanAction.getListParametersBudgeting] END <<<N");
        return listParamBudgeting;
    }

    public List<PerhitunganBudgeting> getListPendapatanTindakan(String branchId, String tahun, String tipe){
        logger.info("[BgPendapatanAction.getListParameterBudgeting] START >>>");
        String bulan = CommonUtil.getDateParted(new Date(System.currentTimeMillis()), "MONTH");
        Integer intBulanLalu = Integer.valueOf(bulan) - 1;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        return budgetingPerhitunganBo.getListPendapatanTindakan(branchId, intBulanLalu.toString(), tahun, tipe);
    }

    public List<PerhitunganBudgeting> getListPendapatanObat(String branchId, String tahun, String tipe){
        logger.info("[BgPendapatanAction.getListPendapatanObat] START >>>");
        String bulan = CommonUtil.getDateParted(new Date(System.currentTimeMillis()), "MONTH");
        Integer intBulanLalu = Integer.valueOf(bulan) - 1;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        return budgetingPerhitunganBo.getListPendapatanObat(branchId, intBulanLalu.toString(), tahun, tipe);
    }

    public CrudResponse setPerhitunganToSession(String idParam, String stPerhitungan) throws JSONException{
        logger.info("[BgPendapatanAction.setPerhitunganToSession] START >>>");

        CrudResponse response = new CrudResponse();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionParam = (List<ParameterBudgeting>) session.getAttribute("listOfParam");
        List<ItAkunPerhitunganBudgetingEntity> sessionPerhitungan = (List<ItAkunPerhitunganBudgetingEntity>) session.getAttribute("listOfPerhitungan");

        // hitung perhitungan budgeting
        if (sessionPerhitungan != null){
            // dari lambda, jika ada hapus dahulu inputkan nilai baru;
            List<ItAkunPerhitunganBudgetingEntity> listPerhitungan = sessionPerhitungan.stream().filter(p->p.getIdParameterBudgeting().equalsIgnoreCase(idParam)).collect(Collectors.toList());
            if (listPerhitungan.size() > 0 ){
                for (ItAkunPerhitunganBudgetingEntity perhitungan : listPerhitungan){
                    sessionPerhitungan.remove(perhitungan);
                }
            }
        } else {
            // jika tidak ada set list baru;
            sessionPerhitungan = new ArrayList<>();
        }

        // inputkan nilai baru dari st JSON;
        JSONArray json = new JSONArray(stPerhitungan);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            ItAkunPerhitunganBudgetingEntity perhitungan = new ItAkunPerhitunganBudgetingEntity();
            if (!"".equalsIgnoreCase(obj.get("nilai").toString())){
                perhitungan.setNilai(obj.get("nilai") == null ? new BigDecimal(0) : new BigDecimal(obj.get("nilai").toString()) );
            }
            if (!"".equalsIgnoreCase(obj.get("opr").toString())){
                perhitungan.setOperator(obj.get("opr").toString());
            }
            perhitungan.setUrutan(i);
            perhitungan.setIdParameterBudgeting(idParam);

            // tambahkan ke session perhitungan
            sessionPerhitungan.add(perhitungan);
        }

        // hitung dari list filter berdasarkan id Params;
        // hasil perhitungan untuk nilai pada parameter budgeting;
        List<ItAkunPerhitunganBudgetingEntity> listPerhitungan = sessionPerhitungan.stream().filter(p->p.getIdParameterBudgeting().equalsIgnoreCase(idParam)).collect(Collectors.toList());
        if (listPerhitungan.size() > 0){

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
            BigDecimal nilaiBudgeting = budgetingPerhitunganBo.hitungNilaiBudgeting(listPerhitungan);

            // set nilai ke session Params
            if (sessionParam != null){
                for (ParameterBudgeting parameterBudgeting : sessionParam){
                    if (idParam.equalsIgnoreCase(parameterBudgeting.getId())){
                        parameterBudgeting.setNilaiTotal(nilaiBudgeting);
                        break;
                    }
                }
            }
        }

        session.removeAttribute("listOfParam");
        session.removeAttribute("listOfPerhitungan");
        session.setAttribute("listOfParam", sessionParam);
        session.setAttribute("listOfPerhitungan", sessionPerhitungan);

        logger.info("[BgPendapatanAction.setPerhitunganToSession] END <<<");
        response.setStatus("success");
        return response;
    }

    public CrudResponse saveAdd(String unit, String tahun, String tipe){
        logger.info("[BgPendapatanAction.saveAdd] START >>>");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();

        CrudResponse response = new CrudResponse();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionParam = (List<ParameterBudgeting>) session.getAttribute("listOfParam");
        List<ItAkunPerhitunganBudgetingEntity> sessionPerhitungan = (List<ItAkunPerhitunganBudgetingEntity>) session.getAttribute("listOfPerhitungan");
        List<ItAkunNilaiParameterBudgetingEntity> sessionNilaiParam = (List<ItAkunNilaiParameterBudgetingEntity>) session.getAttribute("listOfNilaiParam");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        // insert to perhitungan budgeting;
        PerhitunganBudgeting perhitunganBudgeting = new PerhitunganBudgeting();
        perhitunganBudgeting.setCreatedDate(time);
        perhitunganBudgeting.setCreatedWho(userLogin);
        perhitunganBudgeting.setLastUpdate(time);
        perhitunganBudgeting.setLastUpdateWho(userLogin);
        perhitunganBudgeting.setFlag("Y");
        perhitunganBudgeting.setAction("C");
        perhitunganBudgeting.setTahun(tahun);
        perhitunganBudgeting.setBranchId(unit);

        try {
            budgetingPerhitunganBo.saveAddPerhitunganBudgeting( new ArrayList<>(),sessionPerhitungan, perhitunganBudgeting);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.info("[BgPendapatanAction.saveAdd] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[BgPendapatanAction.saveAdd] ERROR " + e);
        }

//        // convert ke list budgeting dan insert ke table budgeting
//        // set status draft dan nilainya
//        List<Budgeting> budgetingList = convertParamToListBudgeting(sessionParam, tahun, unit);
//        Budgeting budgeting = new Budgeting();
//        budgeting.setBranchId(unit);
//        budgeting.setTahun(tahun);
//        budgeting.setStatus("ADD_DRAFT");
//        budgeting.setTipe(tipe);
//        budgeting.setFlag("Y");
//        budgeting.setAction("C");
//        budgeting.setCreatedDate(time);
//        budgeting.setCreatedWho(userLogin);
//        budgeting.setLastUpdate(time);
//        budgeting.setLastUpdateWho(userLogin);
//
//        try {
//            budgetingBo.saveAddBudgeting(budgetingList, new ArrayList<>(), new ArrayList<>(), "DRAFT", budgeting);
//            response.setStatus("success");
//        } catch (GeneralBOException e){
//            logger.info("[BgPendapatanAction.saveAdd] ERROR ", e);
//            response.setStatus("error");
//            response.setMsg("[BgPendapatanAction.saveAdd] ERROR " + e);
//        }
        logger.info("[BgPendapatanAction.saveAdd] END <<<");
        return response;
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
                    budgeting.setTipe("tahunan");
                    budgeting.setNilaiTotal(param.getNilaiTotal());
                    budgetingList.add(budgeting);
                } else {
                    boolean notFound = true;
                    for (Budgeting bg : budgetingList){
                        if (param.getRekeningId().equalsIgnoreCase(bg.getRekeningId())){
                            bg.setNilaiTotal(bg.getNilaiTotal().add(param.getNilaiTotal()));
                            notFound = false;
                            break;
                        }
                    }
                    if (notFound){
                        budgeting.setKodeRekening(getKodeRekeningById(param.getRekeningId()).getKodeRekening());
                        budgeting.setTahun(tahun);
                        budgeting.setBranchId(unit);
                        budgeting.setRekeningId(param.getRekeningId());
                        budgeting.setTipe("tahunan");
                        budgeting.setNilaiTotal(param.getNilaiTotal());
                        budgetingList.add(budgeting);
                    }
                }
            }
        }
        return budgetingList;
    }

    private ImKodeRekeningEntity getKodeRekeningById(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        return kodeRekeningBo.getKodeRekeningById(id);
    }

    public List<ParameterBudgeting> getListParameterByIdKategori(String idKategori){
        logger.info("[BgPendapatanAction.saveAdd] getListParameterByIdKategori >>>");

        List<ParameterBudgeting> results = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
        parameterBudgeting.setIdKategoriBudgeting(idKategori);
        List<ParameterBudgeting> parameterBudgetings = budgetingPerhitunganBo.getSearchListParameterBudgeting(parameterBudgeting);
        if (parameterBudgetings.size() > 0){
            for (ParameterBudgeting dataParam : parameterBudgetings){
                results.add(dataParam);
            }
        }

        logger.info("[BgPendapatanAction.getListParameterByIdKategori] END <<<");
        return results;
    }


}